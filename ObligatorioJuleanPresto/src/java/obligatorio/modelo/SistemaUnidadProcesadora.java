/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.modelo;

import java.util.ArrayList;
import persistencia.BaseDatos;
import persistencia.MapeadorUnidadProcesadora;
import persistencia.Persistencia;

/**
 *
 * @author Usuario
 */
public class SistemaUnidadProcesadora {
    
    private ArrayList<UnidadProcesadora> unidades;
    
    public SistemaUnidadProcesadora(){
      this.unidades = new ArrayList();
    }
    
    public void agregarUnidad(UnidadProcesadora u){
       this.unidades.add(u);
    }

    public ArrayList<UnidadProcesadora> getUnidades() {
        return unidades;
    }    

    public void cargarDatos() {
        this.conectarBD();
        this.cargarUnidades();
    }

    private void conectarBD() {
        String url = "jdbc:mysql://localhost/obligatoriojuleanpresto";
        BaseDatos bd = BaseDatos.getInstancia();
        bd.conectarse("com.mysql.jdbc.Driver", url, "root", "root");
    }

    private void cargarUnidades() {
        unidades.addAll(Persistencia.getInstancia().obtenerTodos(new MapeadorUnidadProcesadora()));
    }
}
