package segura.taylor.bl.entidades;

import java.time.LocalDate;
import java.util.ArrayList;

public class CuentaAhorroProgramado extends Cuenta{
    //Variables
    private double montoDebito;
    private CuentaCorriente cuentaCorriente;

    //Propiedades
    public double getMontoDebito() {
        return montoDebito;
    }
    public void setMontoDebito(double montoDebito) {
        this.montoDebito = montoDebito;
    }

    public CuentaCorriente getCuentaCorriente() {
        return cuentaCorriente;
    }
    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    //Constructores
    public CuentaAhorroProgramado(int numeroCuenta, LocalDate fechaApertura, double minDepositoInicial, double saldo, double tasaInteres, ArrayList<Movimiento> movimientos, Cliente duenno, double montoDebito, CuentaCorriente cuentaCorriente) {
        super(numeroCuenta, fechaApertura, minDepositoInicial, saldo, tasaInteres, movimientos, duenno);
        this.montoDebito = montoDebito;
        this.cuentaCorriente = cuentaCorriente;
    }

    //Metodos
    @Override
    public String toString() {
        return "CuentaAhorroProgramado{" +
                "montoDebito=" + montoDebito +
                ", cuentaCorriente=" + cuentaCorriente +
                ", numeroCuenta=" + numeroCuenta +
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
