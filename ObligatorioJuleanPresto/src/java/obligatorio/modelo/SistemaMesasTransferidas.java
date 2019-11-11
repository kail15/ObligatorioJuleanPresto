
package obligatorio.modelo;

import java.util.ArrayList;

public class SistemaMesasTransferidas {

    private ArrayList<MesaTransferida> mesas;

    public ArrayList<MesaTransferida> getMesas() {
        return mesas;
    }    

    public SistemaMesasTransferidas() {
        this.mesas = new ArrayList();
    } 

    public void agregarMesa(MesaTransferida mesa) {
        this.mesas.add(mesa);
    }
    
    public void elimiarMesa(MesaTransferida mesa){       
      this.mesas.removeIf(m -> (m.getNumero() == mesa.getNumero()));
    }
}
