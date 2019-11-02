/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Mozo extends Usuario{
    private List<Mesa> mesas = new ArrayList<>();
    
    public Mozo(String nombreUsuario, String password, String nombreCompleto) {
        super(nombreUsuario, password, nombreCompleto);
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }
    
    @Override
    public void agregarMesa(Mesa mesa){
       this.mesas.add(mesa);
    }   
    
}
