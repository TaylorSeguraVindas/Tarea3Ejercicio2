package segura.taylor.bl.entidades;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Cuenta {
    //Variables
    protected int numeroCuenta;
    protected LocalDate fechaApertura;
    protected double minDepositoInicial;
    protected double saldo;
    protected double tasaInteres;
    protected ArrayList<Movimiento> movimientos;
    protected Cliente duenno;

    //Propiedades
    public int getNumeroCuenta() {
        return numeroCuenta;
    }
    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }
    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public double getMinDepositoInicial() {
        return minDepositoInicial;
    }
    public void setMinDepositoInicial(double minDepositoInicial) {
        this.minDepositoInicial = minDepositoInicial;
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

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }
    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public Cliente getDuenno() {
        return duenno;
    }
    public void setDuenno(Cliente duenno) {
        this.duenno = duenno;
    }

    //Constructores
    public Cuenta(int numeroCuenta, LocalDate fechaApertura, double minDepositoInicial, double saldo, double tasaInteres, ArrayList<Movimiento> movimientos, Cliente duenno) {
        this.numeroCuenta = numeroCuenta;
        this.fechaApertura = fechaApertura;
        this.minDepositoInicial = minDepositoInicial;
        this.saldo = saldo;
        this.tasaInteres = tasaInteres;
        this.movimientos = movimientos;
        this.duenno = duenno;
    }

    //Metodos
    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta=" + numeroCuenta +
                ", fechaApertura=" + fechaApertura +
                ", minDepositoInicial=" + minDepositoInicial +
                ", saldo=" + saldo +
                ", tasaInteres=" + tasaInteres +
                ", movimientos=" + movimientos +
                ", duenno=" + duenno +
                '}';
    }

    public abstract boolean puedeRealizarMovimiento(Movimiento pMovimiento);
    public abstract void registrarMovimiento(Movimiento pMovimiento);
}
