package segura.taylor.bl.entidades;

import segura.taylor.bl.enums.EnumTipoCuenta;
import segura.taylor.bl.enums.EnumTipoMovimiento;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class CuentaAhorroProgramado extends Cuenta{
    //Constantes
    public static final double constTasaIntereses = 0.14;
    public static final double constMinDepositoInicial = 0.0;

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
    public CuentaAhorroProgramado() {
        this.tipoCuenta = EnumTipoCuenta.AHORRO_PROGRAMADO;
    }
    public CuentaAhorroProgramado(String[] datos) {
        this.tipoCuenta = EnumTipoCuenta.AHORRO_PROGRAMADO;
        this.numeroCuenta = datos[1];
        this.fechaApertura = LocalDate.parse(datos[2]);
        this.saldo = Double.parseDouble(datos[3]);
        this.montoDebito = Double.parseDouble((datos[4]));
    }
    public CuentaAhorroProgramado(String numeroCuenta, LocalDate fechaApertura, double saldo, double montoDebito, Cliente duenno, CuentaCorriente cuentaCorriente) {
        super(numeroCuenta, fechaApertura, saldo, CuentaAhorroProgramado.constTasaIntereses, duenno);
        this.tipoCuenta = EnumTipoCuenta.AHORRO_PROGRAMADO;

        this.montoDebito = montoDebito;
        this.cuentaCorriente = cuentaCorriente;
    }

    //Metodos
    @Override
    public String toString() {
        return "CuentaAhorroProgramado{" +
                "numeroCuenta=" + numeroCuenta +
                ", fechaApertura=" + fechaApertura +
                ", saldo=" + saldo +
                ", tasaInteres=" + tasaInteres +
                ", montoDebito=" + montoDebito +
                ", duenno=" + duenno +
                ", cuentaCorriente=" + cuentaCorriente +
                '}';
    }

    @Override
    public boolean puedeRealizarMovimiento(Movimiento pMovimiento) {
        if(pMovimiento.getTipo().equals(EnumTipoMovimiento.RETIRO)) {
            //Solo se puede retirar si ha pasado un año desde la fecha en que se creó.
            LocalDate fechaActual = LocalDate.now();
            int annos = Period.between(this.fechaApertura, fechaActual).getYears();

            return (annos > 1);
        }

        return true;
    }

    @Override
    public String toCSV() {
        String datos = this.tipoCuenta + "," +
                this.numeroCuenta + "," +
                this.fechaApertura.toString() + "," +
                this.saldo + "," +
                this.montoDebito + "," +
                this.duenno.getId() + "," +
                this.cuentaCorriente.getNumeroCuenta();
        return datos;
    }
}
