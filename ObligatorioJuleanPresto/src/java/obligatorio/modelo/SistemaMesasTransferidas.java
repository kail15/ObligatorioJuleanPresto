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
public class SistemaMesasTransferidas {

    private ArrayList<MesaTransferida> mesas;

    public void agregarMesa(MesaTransferida mesa) {
        this.mesas.add(mesa);
    }
    
    public void elimiarMesa(MesaTransferida mesa){
        this.mesas.remove(mesa);
    }
}
