package segura.taylor.controlador;

import segura.taylor.bl.entidades.*;
import segura.taylor.bl.enums.EnumTipoCuenta;
import segura.taylor.bl.gestor.GestorClientes;
import segura.taylor.bl.gestor.GestorCuentas;
import segura.taylor.ui.UI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controlador {
    GestorClientes gestorClientes = new GestorClientes();
    GestorCuentas gestorCuentas = new GestorCuentas();

    UI ui = new UI();

    public void iniciarPrograma() {
        //Pruebas
        //pruebaGuardarMateriales();
        //pruebaGuardarUsuarios();

        int opcion;

        do {
            opcion = mostrarMenu();
            procesarOpcion(opcion);
        } while (opcion != 7);
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
                registrarCuenta();
                break;
            case 4:
                listarCuentas();
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

    private void registrarCuenta() {
        boolean resultado = false;

        //Dueño
        Optional<Cliente> clienteEncontrado;
        Cliente duenno;

        do {
            listarClientes();
            ui.imprimir("Id del cliente dueño: ");
            String idCliente = ui.leerLinea();

            clienteEncontrado = gestorClientes.buscarPorId(idCliente);

            if(!clienteEncontrado.isPresent()) {
                ui.imprimirLinea("No se pudo encontrar ningun cliente con ese id");
            }
        } while (!clienteEncontrado.isPresent());

        duenno = clienteEncontrado.get();

        //Tipo cuenta
        EnumTipoCuenta tipoCuenta = EnumTipoCuenta.CORRIENTE;
        int opcionTipoCuenta = 0;

        do {
            ui.imprimirLinea("Seleccione un tipo de cuenta");
            ui.imprimirLinea("1. Corriente");
            ui.imprimirLinea("2. Ahorro");
            ui.imprimirLinea("3. Ahorro programado");
            ui.imprimir("Su opcion: ");
            opcionTipoCuenta = ui.leerEntero();
        } while (opcionTipoCuenta < 0 || opcionTipoCuenta > 3);

        //Info cuenta
        ui.imprimir("Numero de cuenta: ");
        String numeroCuenta = ui.leerLinea();
        LocalDate fechaApertura = LocalDate.now();

        switch (opcionTipoCuenta) {
            case 1:
                CuentaCorriente cuentaCorriente = new CuentaCorriente(numeroCuenta, fechaApertura, 0.0, duenno);
                resultado = gestorCuentas.guardarcuenta(cuentaCorriente);
                break;
            case 2:
                CuentaAhorro cuentaAhorro = new CuentaAhorro(numeroCuenta, fechaApertura, 0.0, duenno);
                resultado = gestorCuentas.guardarcuenta(cuentaAhorro);
                break;
            case 3:
                Optional<Cuenta> cuentaCorrienteEncontrada;

                do {
                    listarCuentas();
                    ui.imprimir("Numero de la cuenta corriente: ");
                    String idCuentaCorriente = ui.leerLinea();
                    cuentaCorrienteEncontrada = gestorCuentas.buscarPorId(idCuentaCorriente);
                } while (!cuentaCorrienteEncontrada.isPresent());

                CuentaCorriente cuentaCorrienteObjetivo = (CuentaCorriente) cuentaCorrienteEncontrada.get();

                ui.imprimir("Monto debido: ");
                double montoDebito = ui.leerDouble();

                CuentaAhorroProgramado cuentaAhorroProgramado = new CuentaAhorroProgramado(numeroCuenta, fechaApertura, 0.0, montoDebito, duenno, cuentaCorrienteObjetivo);
                resultado = gestorCuentas.guardarcuenta(cuentaAhorroProgramado);
                break;
        }

        if(resultado) {
            ui.imprimirLinea("Cuenta registrada correctamente");
        } else {
            ui.imprimirLinea("Ocurrió un error al registrar la cuenta");
        }
    }

    private void listarCuentas() {
        List<Cuenta> cuentas = gestorCuentas.listarCuentas();

        for (Cuenta cuenta : cuentas) {
            ui.imprimirLinea(cuenta.toString());
        }
    }
}
