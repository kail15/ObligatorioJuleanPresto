
package obligatorio.controladores;

import obligatorio.fachada.Fachada;
import obligatorio.modelo.Mozo;
import obligatorio.modelo.Usuario;


public class ControladorMozo {
    
    private Fachada fachada;
    private VistaMozo vista;
    // private Mozo mozo; 
    private Usuario mozo; // cambiar por mozo, es de prueba

    public ControladorMozo(VistaMozo vista, Usuario mozo) {
        this.fachada = Fachada.getInstancia();
        this.vista = vista;
        this.mozo = mozo;
        this.vista.obtenerMozo(mozo.getUserId());
    }
    
    
    
    
}
