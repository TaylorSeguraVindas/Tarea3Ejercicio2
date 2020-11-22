package segura.taylor.bl.gestor;

import segura.taylor.bl.entidades.Movimiento;
import segura.taylor.bl.persistencia.MovimientoFAO;

import java.util.List;
import java.util.Optional;

public class GestorMovimientos {
    MovimientoFAO movimientoFAO = new MovimientoFAO();

    public boolean guardarMovimiento(Movimiento nuevoMovimiento) {
        return movimientoFAO.guardarNuevoMovimiento(nuevoMovimiento);
    }

    public List<Movimiento> listarMovimientos() {
        return movimientoFAO.listarTodos();
    }

    public Optional<Movimiento> buscarPorId(String id) {
        return movimientoFAO.buscarPorId(id);
    }
}
