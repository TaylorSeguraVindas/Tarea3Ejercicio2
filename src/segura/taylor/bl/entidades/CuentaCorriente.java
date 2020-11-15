package segura.taylor.bl.entidades;

import java.time.LocalDate;
import java.util.ArrayList;

public class CuentaCorriente extends Cuenta {
    //Constantes
    public static double interesesCorriente = 0.14;

    //Variables
    //Propiedades

    //Constructores
    public CuentaCorriente(int numeroCuenta, LocalDate fechaApertura, double minDepositoInicial, double saldo, ArrayList<Movimiento> movimientos, Cliente duenno) {
        super(numeroCuenta, fechaApertura, minDepositoInicial, saldo, interesesCorriente, movimientos, duenno);
    }

    @Override
    public boolean puedeRealizarMovimiento(Movimiento pMovimiento) {
        return false;
    }

    @Override
    public void registrarMovimiento(Movimiento pMovimiento) {

    }
}
