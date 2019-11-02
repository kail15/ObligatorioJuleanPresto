
package obligatorio.controladores;

//OJP
import obligatorio.fachada.Fachada;

public class ControladorLogin {
    
    private Fachada fachada;
    private VistaLogin vista;
    
    public ControladorLogin(VistaLogin vista){
        this.vista = vista;
       // this.fachada = Fachada.getInstancia();               
    }  
    
}
