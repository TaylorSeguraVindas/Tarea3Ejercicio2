package segura.taylor.bl.persistencia;

import segura.taylor.bl.entidades.Movimiento;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovimientoFAO {
    private final String directorioMovimientos = "c:\\dev\\Movimientos.csv";

    private CuentaFAO cuentaFAO = new CuentaFAO();

    public boolean guardarNuevoMovimiento(Movimiento nuevoMovimiento) {
        boolean idRepetido = buscarPorId(nuevoMovimiento.getId()).isPresent();

        if(!idRepetido) {
            ArrayList<String> lines = new ArrayList<>();
            lines.add(nuevoMovimiento.toCSV());

            try {
                Files.write(Paths.get(directorioMovimientos), lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public List<Movimiento> listarTodos() {
        ArrayList<Movimiento> result = new ArrayList<>();
        BufferedReader reader;

        File archivoUsuarios = new File(directorioMovimientos);
        if(archivoUsuarios.exists()) {
            try {
                reader = new BufferedReader(new FileReader(directorioMovimientos));
                String currentLine = reader.readLine();
                while (currentLine != null) {
                    String[] datos = currentLine.split(",");
                    result.add(leerMovimientoCSV(datos));

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

    public Optional<Movimiento> buscarPorId(String id) {
        BufferedReader reader;

        File archivoUsuarios = new File(directorioMovimientos);
        if(archivoUsuarios.exists()) {
            try {
                reader = new BufferedReader(new FileReader(directorioMovimientos));
                String currentLine = reader.readLine();
                while (currentLine != null) {
                    String[] datos = currentLine.split(",");
                    //Filtrar por id.
                    if (datos[0].equals(id)) {
                        return Optional.of(leerMovimientoCSV(datos));
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

    private Movimiento leerMovimientoCSV(String[] datosLinea){
        Movimiento movimiento = new Movimiento(datosLinea);
        movimiento.setCuentaModificada(cuentaFAO.buscarPorId(datosLinea[5]).get());
        return movimiento;
    }

}
