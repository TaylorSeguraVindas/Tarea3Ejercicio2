package segura.taylor.bl.entidades;

import segura.taylor.bl.enums.EnumTipoCuenta;
import segura.taylor.bl.enums.EnumTipoMovimiento;
import segura.taylor.bl.interfaces.SerializableCSV;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Cuenta implements SerializableCSV {
    //Variables
    protected EnumTipoCuenta tipoCuenta;
    protected String numeroCuenta;
    protected LocalDate fechaApertura;
    protected double saldo;
    protected double tasaInteres;
    protected Cliente duenno;

    //Propiedades
    public EnumTipoCuenta getTipoCuenta() { return this.tipoCuenta; }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }
    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }
    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public Cliente getDuenno() {
        return duenno;
    }
    public void setDuenno(Cliente duenno) {
        this.duenno = duenno;
    }

    //Constructores
    public Cuenta(){}
    public Cuenta(String[] datos){}
    public Cuenta(String numeroCuenta, LocalDate fechaApertura, double saldo, double tasaInteres, Cliente duenno) {
        this.numeroCuenta = numeroCuenta;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
        this.tasaInteres = tasaInteres;
        this.duenno = duenno;
    }

    //Metodos
    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta=" + numeroCuenta +
                ", fechaApertura=" + fechaApertura +
                ", saldo=" + saldo +
                ", tasaInteres=" + tasaInteres +
                ", duenno=" + duenno +
                '}';
    }

    public abstract boolean puedeRealizarMovimiento(Movimiento pMovimiento);

    public void registrarMovimiento(Movimiento pMovimiento) throws Exception {
        if (pMovimiento.getTipo().equals(EnumTipoMovimiento.DEPOSITO)) {
            this.saldo += pMovimiento.getMonto();
        }

        if (pMovimiento.getTipo().equals(EnumTipoMovimiento.RETIRO)) {
            if((this.saldo-pMovimiento.getMonto()) >= 0.0) {
                this.saldo -= pMovimiento.getMonto();
            } else {
                throw new Exception("No hay suficiente saldo en la cuenta para realizar el retiro");
            }
        }
    }
}
