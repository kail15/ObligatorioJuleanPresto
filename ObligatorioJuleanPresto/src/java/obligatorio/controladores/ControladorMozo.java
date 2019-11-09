
package obligatorio.controladores;

import java.util.ArrayList;
import java.util.List;
import obligatorio.fachada.Fachada;
import obligatorio.modelo.Mesa;
import obligatorio.modelo.Mozo;
import obligatorio.modelo.Usuario;


public class ControladorMozo {
    
    private Fachada fachada;
    private VistaMozo vista;
    private Usuario mozoLogueado; 

    public ControladorMozo(VistaMozo vista, Usuario mozo) {
        this.fachada = Fachada.getInstancia();
        this.vista = vista;
        this.mozoLogueado = mozo;
        this.vista.obtenerMozo(mozo.getUserId());
        this.vista.obtenerProductos(this.fachada.obtenerProductos());
    }

    public Usuario getMozoLogueado() {
        return mozoLogueado;
    }

    public void setMozoLogueado(Usuario mozoLogueado) {
        this.mozoLogueado = mozoLogueado;
    }
    
    
    public List<Mesa> CambiarEstadoMesa(int id, boolean estado)
    {   List<Mesa> mesasMozo;
        mesasMozo =  this.mozoLogueado.CambiarEstadoMesa(id, estado);  
        return mesasMozo;
    }
    
}
