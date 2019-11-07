
package obligatorio.controladores;

//OJP
import obligatorio.exceptions.CredencialesInvalidasException;
import obligatorio.exceptions.UsuarioInactivoException;
import obligatorio.exceptions.UsuarioLogueadoException;
import obligatorio.fachada.Fachada;
import obligatorio.modelo.Usuario;

public class ControladorLogin {
    
    private Fachada fachada;
    private VistaLogin vista;
    
    public ControladorLogin(VistaLogin vista){
        this.vista = vista;
         this.fachada = Fachada.getInstancia();               
    }  
    
    public void login(String username, String password){     
        try {            
           Usuario usuario = this.fachada.login(username, password);                        
             vista.ingresarUsuario(usuario);
            
        } catch (CredencialesInvalidasException | UsuarioInactivoException ex) {   
            vista.mostrarError(ex.getMessage());
            
        }catch(UsuarioLogueadoException exMessage){
           vista.mostrarError(exMessage.getMessage());
        }
        
        catch (Exception e){
            vista.mostrarError("Error inesperado en el sistema");
        }
        
    }
    
}
