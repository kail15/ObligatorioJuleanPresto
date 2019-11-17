package obligatorio.modelo;

import java.util.ArrayList;

public class SistemaClientes {

    private ArrayList<Cliente> clientes;

    public SistemaClientes() {
        this.clientes = new ArrayList();
    }
    
    public void agregarCliente(Cliente cliente){
      this.clientes.add(cliente);
    }

    public Cliente clienteById(int id) {
        Cliente cliente = null;

        for (Cliente cli : this.clientes) {
            if (cli.getId() == id) {
                cliente = cli;
                break;
            }
        }
        return cliente;
    }

}
