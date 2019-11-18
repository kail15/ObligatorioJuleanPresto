/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.modelo;

import java.util.ArrayList;
import persistencia.BaseDatos;
import persistencia.MapeadorMesa;
import persistencia.Persistencia;

/**
 *
 * @author Usuario
 */
public class SistemaMesa {
    private ArrayList<Mesa> mesas;

    public SistemaMesa() {
        mesas = new ArrayList<>();
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(ArrayList<Mesa> mesas) {
        this.mesas = mesas;
    }

    public void cargarDatos() {
        this.conectarBD();
        this.cargarMesas(); 
        System.out.println("DATOS CARGADOS");
    }

    private void conectarBD() {
        String url = "jdbc:mysql://localhost:3306/obligatoriojuleanpresto";
        BaseDatos bd = BaseDatos.getInstancia();
        bd.conectarse("com.mysql.jdbc.Driver", url, "root", "root");
    }

    private void cargarMesas() {
        mesas = Persistencia.getInstancia().obtenerTodos(new MapeadorMesa());
        int test = 0;
    }
    
    
    
    
}
