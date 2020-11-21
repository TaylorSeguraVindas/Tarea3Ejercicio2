package segura.taylor.bl.entidades;

import segura.taylor.bl.enums.EnumTipoCuenta;

import java.time.LocalDate;
import java.util.ArrayList;

public class CuentaCorriente extends Cuenta {
    //Constantes
    public static final double constTasaIntereses = 0.14;
    public static final double constMinDepositoInicial = 50000.0;

    //Variables
    //Propiedades

    //Constructores
    public CuentaCorriente() {
        this.tipoCuenta = EnumTipoCuenta.CORRIENTE;
        this.tasaInteres = CuentaCorriente.constTasaIntereses;
    }
    public CuentaCorriente(String[] datos) {
        this.tipoCuenta = EnumTipoCuenta.CORRIENTE;
        this.numeroCuenta = datos[1];
        this.fechaApertura = LocalDate.parse(datos[2]);
        this.saldo = Double.parseDouble(datos[3]);

        this.tasaInteres = CuentaCorriente.constTasaIntereses;
    }
    public CuentaCorriente(String numeroCuenta, LocalDate fechaApertura, double saldo, Cliente duenno) {
        super(numeroCuenta, fechaApertura, saldo, CuentaCorriente.constTasaIntereses, duenno);
        this.tipoCuenta = EnumTipoCuenta.CORRIENTE;
    }

    @Override
    public boolean puedeRealizarMovimiento(Movimiento pMovimiento) {
        return false;
    }

    @Override
    public void registrarMovimiento(Movimiento pMovimiento) {

    }

    @Override
    public String toString() {
        return "CuentaCorriente{" +
                "tipoCuenta=" + tipoCuenta +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", fechaApertura=" + fechaApertura +
                ", saldo=" + saldo +
                ", tasaInteres=" + tasaInteres +
                ", duenno=" + duenno +
                '}';
    }

    @Override
    public String toCSV() {
        String datos = this.tipoCuenta + "," +
                this.numeroCuenta + "," +
                this.fechaApertura.toString() + "," +
                this.saldo + "," +
                this.duenno.getId();
        return datos;
    }
}
