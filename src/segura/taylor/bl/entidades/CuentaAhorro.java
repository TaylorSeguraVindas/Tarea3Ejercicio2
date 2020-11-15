package segura.taylor.bl.entidades;

import java.time.LocalDate;
import java.util.ArrayList;

public class CuentaAhorro extends Cuenta{
    public static double interesesAhorro = 0.12;
    //Variables
    //Propiedades

    //Constructores
    public CuentaAhorro(int numeroCuenta, LocalDate fechaApertura, double minDepositoInicial, double saldo, ArrayList<Movimiento> movimientos, Cliente duenno) {
        super(numeroCuenta, fechaApertura, minDepositoInicial, saldo, interesesAhorro, movimientos, duenno);
    }

    //Metodos
    @Override
    public String toString() {
        return "CuentaAhorro{" +
                "numeroCuenta=" + numeroCuenta +
                ", fechaApertura=" + fechaApertura +
                ", minDepositoInicial=" + minDepositoInicial +
                ", saldo=" + saldo +
                ", tasaInteres=" + tasaInteres +
                ", movimientos=" + movimientos +
                ", duenno=" + duenno +
                '}';
    }

    @Override
    public boolean puedeRealizarMovimiento(Movimiento pMovimiento) {
        return false;
    }
    @Override
    public void registrarMovimiento(Movimiento pMovimiento) {

    }
}
