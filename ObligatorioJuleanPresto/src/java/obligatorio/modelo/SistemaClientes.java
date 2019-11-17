package obligatorio.modelo;

import java.util.ArrayList;
import obligatorio.exceptions.ClienteException;

public class SistemaClientes {

    private ArrayList<Cliente> clientes;

    public SistemaClientes() {
        this.clientes = new ArrayList();
    }

    public void agregarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public Cliente clienteById(int id) throws ClienteException {
        Cliente cliente = null;

        for (Cliente cli : this.clientes) {
            if (cli.getId() == id) {
                cliente = cli;
                break;
            }
        }
        if (cliente != null) {
            return cliente;
        }else{
           throw new ClienteException("No se encontro el cliente");
        }

    }

}
