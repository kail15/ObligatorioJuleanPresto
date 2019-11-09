/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.modelo;

import java.util.ArrayList;

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
}
