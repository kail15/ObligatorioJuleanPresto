
package obligatorio.controladores;

import obligatorio.fachada.Fachada;
import obligatorio.modelo.Usuario;
import observer.Observable;
import observer.Observador;


public class ControladorGestor implements Observador {

    private Fachada fachada;
    private vistaGestor vista;
    private Usuario gestorLogueado;
    
    public ControladorGestor(vistaGestor vista, Usuario gestor) {
        this.fachada = Fachada.getInstancia();
        this.vista = vista;
        this.gestorLogueado = gestor;
    }  
    
    
    @Override
    public void actualizar(Object o, Observable o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
