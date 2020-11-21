package segura.taylor.controlador;

import segura.taylor.bl.entidades.Cliente;
import segura.taylor.bl.gestor.GestorClientes;
import segura.taylor.ui.UI;

import java.util.List;

public class Controlador {
    GestorClientes gestorClientes = new GestorClientes();

    UI ui = new UI();

    public void iniciarPrograma() {
        //Pruebas
        //pruebaGuardarMateriales();
        //pruebaGuardarUsuarios();

        int opcion;

        do {
            opcion = mostrarMenu();
            procesarOpcion(opcion);
        } while (opcion != 5);
    }

    private int mostrarMenu() {
        ui.imprimirLinea("Bienvenido, seleccione una opción");
        ui.imprimirLinea("1. Registrar cliente");
        ui.imprimirLinea("2. Listar clientes");
        ui.imprimirLinea("3. Registrar cuenta");
        ui.imprimirLinea("4. Listar cuentas");
        ui.imprimirLinea("5. Realizar movimiento");
        ui.imprimirLinea("6. Listar movimientos");
        ui.imprimirLinea("7. Salir");
        ui.imprimir("Su opcion: ");
        int opcion = ui.leerEntero();
        return opcion;
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registrarCliente();
                break;
            case 2:
                listarClientes();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                ui.imprimirLinea("Adios");
                break;
            default:
                ui.imprimirLinea("Opcion invalida");
                break;
        }
    }

    private void registrarCliente()
    {
        ui.imprimirLinea("Id: ");
        String id = ui.leerLinea();
        ui.imprimirLinea("Nombre: ");
        String nombre = ui.leerLinea();
        ui.imprimirLinea("Direccion: ");
        String direccion = ui.leerLinea();

        boolean resultado = gestorClientes.guardarCliente(new Cliente(id, nombre, direccion));

        if (resultado) {
            ui.imprimirLinea("Cliente registrado correctamente");
        } else {
            ui.imprimirLinea("Ya existe un cliente con el id especificado");
        }
    }

    private void listarClientes() {
        List<Cliente> clientes = gestorClientes.listarClientes();

        for (Cliente cliente : clientes) {
            ui.imprimirLinea(cliente.toString());
        }
    }

}
