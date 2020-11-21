package segura.taylor.bl.gestor;

import segura.taylor.bl.entidades.Cliente;
import segura.taylor.bl.persistencia.ClienteFAO;

import java.util.List;
import java.util.Optional;

public class GestorClientes {
    ClienteFAO clienteFAO = new ClienteFAO();

    public boolean guardarCliente(Cliente nuevoCliente) {
        return clienteFAO.guardarNuevoCliente(nuevoCliente);
    }

    public List<Cliente> listarClientes() {
        return clienteFAO.listarTodos();
    }

    public Optional<Cliente> buscarPorId(String id) {
        return clienteFAO.buscarPorId(id);
    }
}
