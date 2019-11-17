
package obligatorio.modelo;

import java.util.ArrayList;
import java.util.List;

public class Mozo extends Usuario {

    private List<Mesa> mesas = new ArrayList<>();

    public Mozo(String userId, String nombreUsuario, String password, String nombreCompleto) {
        super(userId, nombreUsuario, password, nombreCompleto);
    }

    public Mozo() {
        super();
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    @Override
    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    @Override
    public void agregarMesa(Mesa mesa) {
        this.mesas.add(mesa);
    }

    @Override
    public List<Mesa> obtenerMesas() {
        return this.getMesas();
    }

    @Override
    public List<Mesa> CambiarEstadoMesa(int id, boolean estado) {
        for (Mesa mesa : this.mesas) {
            if (id == mesa.getNumero()) {
                mesa.setEstado(estado);
                break;
            }
        }
        return this.getMesas();
    }

    @Override
    public void elimiarMesa(Mesa mesa) {
        this.mesas.removeIf(m -> (m.getNumero() == mesa.getNumero()));
    }
    
    @Override
    public boolean validarMesasLogout(Mesa mesa){
        return this.mesas.stream().noneMatch((m) -> (m.getEstado() && m.getNumero() == mesa.getNumero()));        
    }   
    
    @Override
    public Mesa obtenerMesaByNumero(int numero) {
        Mesa mesa = null;
      for(Mesa m : this.mesas){
         if(m.getNumero() == numero ){
            mesa = m;
            break;
         }
      }
      return mesa;
    }
}
