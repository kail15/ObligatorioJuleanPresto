
package obligatorio.controladores;

import obligatorio.fachada.Fachada;
import obligatorio.modelo.Mozo;
import obligatorio.modelo.Usuario;


public class ControladorMozo {
    
    private Fachada fachada;
    private VistaMozo vista;
    // private Mozo mozo; 
    private Usuario mozoLogueado; 

    public ControladorMozo(VistaMozo vista, Usuario mozo) {
        this.fachada = Fachada.getInstancia();
        this.vista = vista;
        this.mozoLogueado = mozo;
        this.vista.obtenerMozo(mozo.getUserId());
    }

    public Usuario getMozoLogueado() {
        return mozoLogueado;
    }

    public void setMozoLogueado(Usuario mozoLogueado) {
        this.mozoLogueado = mozoLogueado;
    }
    
    
    
    public void CambiarEstadoMesa(int id, boolean estado)
    {
        this.mozoLogueado.CambiarEstadoMesa(id, estado);    
    }
    
    
    
    
}
