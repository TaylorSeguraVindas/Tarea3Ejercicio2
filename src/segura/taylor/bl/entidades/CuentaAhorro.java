package segura.taylor.bl.entidades;

import segura.taylor.bl.enums.EnumTipoCuenta;

import java.time.LocalDate;
import java.util.ArrayList;

public class CuentaAhorro extends Cuenta{
    //Constantes
    public static final double constTasaIntereses = 0.14;
    public static final double constMinDepositoInicial = 0.0;

    //Variables
    //Propiedades

    //Constructores
    public CuentaAhorro(){
        this.tipoCuenta = EnumTipoCuenta.AHORRO;
        this.tasaInteres = CuentaAhorro.constTasaIntereses;
    }
    public CuentaAhorro(String[] datos){
        this.tipoCuenta = EnumTipoCuenta.CORRIENTE;
        this.numeroCuenta = datos[1];
        this.fechaApertura = LocalDate.parse(datos[2]);
        this.saldo = Double.parseDouble(datos[3]);

        this.tasaInteres = CuentaAhorro.constTasaIntereses;
    }
    public CuentaAhorro(String numeroCuenta, LocalDate fechaApertura, double saldo, Cliente duenno) {
        super(numeroCuenta, fechaApertura, saldo, CuentaAhorro.constTasaIntereses, duenno);
        this.tipoCuenta = EnumTipoCuenta.AHORRO;
    }

    //Metodos
    @Override
    public String toString() {
        return "CuentaAhorro{" +
                "numeroCuenta=" + numeroCuenta +
                ", fechaApertura=" + fechaApertura +
                ", saldo=" + saldo +
                ", tasaInteres=" + tasaInteres +
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
