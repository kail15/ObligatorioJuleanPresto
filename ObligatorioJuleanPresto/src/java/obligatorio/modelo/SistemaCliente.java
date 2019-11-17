/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.modelo;

import java.util.ArrayList;
import persistencia.BaseDatos;
import persistencia.MapeadorCliente;
import persistencia.Persistencia;

/**
 *
 * @author Usuario
 */
public class SistemaCliente {
    private ArrayList<Cliente> clientes;

    public SistemaCliente() {
        this.clientes = new ArrayList<>();
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void cargarDatos() {
        this.conectarBD();
        this.cargarClientes();
    }

    private void conectarBD() {
        String url = "jdbc:mysql://localhost/obligatoriojuleanpresto";
        BaseDatos bd = BaseDatos.getInstancia();
        bd.conectarse("com.mysql.jdbc.Driver", url, "root", "root");
    }

    private void cargarClientes() {
        clientes.addAll(Persistencia.getInstancia().obtenerTodos(new MapeadorCliente()));
    }
    
    
    
}
