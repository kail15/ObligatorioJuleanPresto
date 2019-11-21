/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.modelo;

import java.util.ArrayList;
import persistencia.BaseDatos;
import persistencia.MapeadorMesa;
import persistencia.MapeadorServicio;
import persistencia.Persistencia;

/**
 *
 * @author Usuario
 */
public class SistemaMesa {

    private ArrayList<Mesa> mesas;
    private ArrayList<Servicio> servicios;

    public SistemaMesa() {
        mesas = new ArrayList<>();
        servicios = new ArrayList<>();
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
    }

    public void guardarServicio(Servicio s) {
        Persistencia.getInstancia().guardar(new MapeadorServicio(s));
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    } 
    

}
