package segura.taylor.bl.gestor;

import segura.taylor.bl.entidades.Cuenta;
import segura.taylor.bl.persistencia.CuentaFAO;

import java.util.List;
import java.util.Optional;

public class GestorCuentas {
    CuentaFAO cuentaFAO = new CuentaFAO();

    public boolean guardarcuenta(Cuenta nuevocuenta) {
        return cuentaFAO.guardarNuevaCuenta(nuevocuenta);
    }

    public List<Cuenta> listarCuentas() {
        return cuentaFAO.listarTodas();
    }

    public Optional<Cuenta> buscarPorId(String id) {
        return cuentaFAO.buscarPorId(id);
    }
}
