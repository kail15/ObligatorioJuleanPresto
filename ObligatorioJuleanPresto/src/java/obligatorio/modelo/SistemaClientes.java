package obligatorio.modelo;

import java.util.ArrayList;
import obligatorio.exceptions.ClienteException;
import persistencia.BaseDatos;
import persistencia.MapeadorCliente;
import persistencia.Persistencia;

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
            if (cli.getId()== id) {
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

    public void cargarDatos() {
        this.conectarBD();
        this.cargarClientes(); 
        System.out.println("DATOS CARGADOS");
    }

    private void conectarBD() {
        String url = "jdbc:mysql://localhost:3306/obligatoriojuleanpresto";
        BaseDatos bd = BaseDatos.getInstancia();
        bd.conectarse("com.mysql.jdbc.Driver", url, "root", "root");
    }

    private void cargarClientes() {
        clientes = Persistencia.getInstancia().obtenerTodos(new MapeadorCliente());
    }

}
