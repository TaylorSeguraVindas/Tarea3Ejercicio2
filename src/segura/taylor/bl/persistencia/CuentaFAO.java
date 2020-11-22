package segura.taylor.bl.persistencia;

import segura.taylor.bl.entidades.Cuenta;
import segura.taylor.bl.entidades.CuentaAhorro;
import segura.taylor.bl.entidades.CuentaAhorroProgramado;
import segura.taylor.bl.entidades.CuentaCorriente;
import segura.taylor.bl.enums.EnumTipoCuenta;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CuentaFAO {
    private final String directorioCuentas = "c:\\dev\\Cuentas.csv";

    private ClienteFAO clienteFAO = new ClienteFAO();

    public boolean guardarNuevaCuenta(Cuenta nuevoCuenta) {
        boolean idRepetido = buscarPorId(nuevoCuenta.getNumeroCuenta()).isPresent();

        if(!idRepetido) {
            ArrayList<String> lines = new ArrayList<>();
            lines.add(nuevoCuenta.toCSV());

            try {
                Files.write(Paths.get(directorioCuentas), lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public boolean modificarCuenta(Cuenta cuentaModificar) {
        ArrayList<String> result = new ArrayList<>();
        BufferedReader reader;

        File archivoCuentas = new File(directorioCuentas);
        if(archivoCuentas.exists()) {
            try {
                int indiceCuenta = -1;
                int cont = 0;

                reader = new BufferedReader(new FileReader(directorioCuentas));
                String currentLine = reader.readLine();
                while (currentLine != null) {
                    String[] datos = currentLine.split(",");
                    result.add(currentLine);

                    //Guardar el indice de la cuenta que se va a modificar
                    if(datos[0].equals(cuentaModificar.getNumeroCuenta())) {
                        indiceCuenta = cont;
                    }

                    currentLine = reader.readLine();
                    cont++;
                }

                //Si la cuenta se encontró
                if(indiceCuenta != -1) {
                    //Se actualiza la información
                    result.set(indiceCuenta, cuentaModificar.toCSV());

                    //Y se sobreescribe todo el archivo para aplicar el cambio
                    try {
                        Files.write(Paths.get(directorioCuentas), result, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                                StandardOpenOption.WRITE);
                        return true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public List<Cuenta> listarTodas() {
        ArrayList<Cuenta> result = new ArrayList<>();
        BufferedReader reader;

        File archivoCuentas = new File(directorioCuentas);
        if(archivoCuentas.exists()) {
            try {
                reader = new BufferedReader(new FileReader(directorioCuentas));
                String currentLine = reader.readLine();
                while (currentLine != null) {
                    String[] datos = currentLine.split(",");
                    result.add(leerCuentaCSV(datos));

                    currentLine = reader.readLine();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public List<Cuenta> listarPorTipo(EnumTipoCuenta tipo) {
        ArrayList<Cuenta> result = new ArrayList<>();
        BufferedReader reader;

        File archivoCuentas = new File(directorioCuentas);
        if(archivoCuentas.exists()) {
            try {
                reader = new BufferedReader(new FileReader(directorioCuentas));
                String currentLine = reader.readLine();
                while (currentLine != null) {
                    String[] datos = currentLine.split(",");
                    //Filtrar por tipo.
                    if (EnumTipoCuenta.valueOf(datos[0]).equals(tipo)) {
                        result.add(leerCuentaCSV(datos));
                    }

                    currentLine = reader.readLine();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public Optional<Cuenta> buscarPorId(String id) {
        BufferedReader reader;

        File archivoCuentas = new File(directorioCuentas);
        if(archivoCuentas.exists()) {
            try {
                reader = new BufferedReader(new FileReader(directorioCuentas));
                String currentLine = reader.readLine();
                while (currentLine != null) {
                    String[] datos = currentLine.split(",");
                    //Filtrar por signatura o id.
                    if (datos[1].equals(id)) {
                        return Optional.of(leerCuentaCSV(datos));
                    }

                    currentLine = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    private Cuenta leerCuentaCSV(String[] datosLinea) {
        EnumTipoCuenta tipoCuenta = EnumTipoCuenta.valueOf(datosLinea[0]);    //El primer atributo siempre define el tipo de Cuenta

        if (EnumTipoCuenta.CORRIENTE.equals(tipoCuenta)) {
            CuentaCorriente cuenta = new CuentaCorriente(datosLinea);
            cuenta.setDuenno(clienteFAO.buscarPorId(datosLinea[4]).get());  //Buscar el dueño de la cuenta basado en el id
            return cuenta;
        }
        if (EnumTipoCuenta.AHORRO.equals(tipoCuenta)) {
            CuentaAhorro cuenta = new CuentaAhorro(datosLinea);
            cuenta.setDuenno(clienteFAO.buscarPorId(datosLinea[4]).get());  //Buscar el dueño de la cuenta basado en el id
            return cuenta;
        }
        if (EnumTipoCuenta.AHORRO_PROGRAMADO.equals(tipoCuenta)) {
            CuentaAhorroProgramado cuenta = new CuentaAhorroProgramado(datosLinea);
            cuenta.setDuenno(clienteFAO.buscarPorId(datosLinea[5]).get());  //Buscar el dueño de la cuenta basado en el id
            cuenta.setCuentaCorriente((CuentaCorriente) buscarPorId(datosLinea[6]).get());
            return cuenta;
        }
        return null;
    }
}
