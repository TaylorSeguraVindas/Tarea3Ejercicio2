package segura.taylor.bl.persistencia;

import segura.taylor.bl.entidades.Cliente;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteFAO {
    private final String directorioClientes = "c:\\dev\\Clientes.csv";

    public boolean guardarNuevoCliente(Cliente nuevoCliente) {
        boolean idRepetido = buscarPorId(nuevoCliente.getId()).isPresent();

        if(!idRepetido) {
            ArrayList<String> lines = new ArrayList<>();
            lines.add(nuevoCliente.toCSV());

            try {
                Files.write(Paths.get(directorioClientes), lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public List<Cliente> listarTodos() {
        ArrayList<Cliente> result = new ArrayList<>();
        BufferedReader reader;

        File archivoUsuarios = new File(directorioClientes);
        if(archivoUsuarios.exists()) {
            try {
                reader = new BufferedReader(new FileReader(directorioClientes));
                String currentLine = reader.readLine();
                while (currentLine != null) {
                    String[] datos = currentLine.split(",");
                    result.add(leerClienteCSV(datos));

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

    public Optional<Cliente> buscarPorId(String id) {
        BufferedReader reader;

        File archivoUsuarios = new File(directorioClientes);
        if(archivoUsuarios.exists()) {
            try {
                reader = new BufferedReader(new FileReader(directorioClientes));
                String currentLine = reader.readLine();
                while (currentLine != null) {
                    String[] datos = currentLine.split(",");
                    //Filtrar por signatura o id.
                    if (datos[0].equals(id)) {
                        return Optional.of(leerClienteCSV(datos));
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

    private Cliente leerClienteCSV(String[] datosLinea){
        return new Cliente(datosLinea);
    }
}
