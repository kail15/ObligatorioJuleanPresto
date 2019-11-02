package obligatorio.fachada;

import java.util.Observable;
import obligatorio.exceptions.CredencialesInvalidasException;
import obligatorio.exceptions.UsuarioInactivoException;
import obligatorio.modelo.SistemaUsuarios;
import obligatorio.modelo.Usuario;

//OJP
public class Fachada extends Observable {

    private SistemaUsuarios sistemaUsuarios;
    private static Fachada instancia;
    
    private Fachada() {
        sistemaUsuarios = new SistemaUsuarios();
       // cargar();
    }
    
    public static Fachada getInstancia() {
        //Implementacion de singleton 
        if(instancia == null){
            instancia = new Fachada();
        }
        return instancia;
    }
    
    public Usuario login(String n, String p) throws CredencialesInvalidasException, UsuarioInactivoException{

        Usuario usuario = sistemaUsuarios.login(n, p);
        if(usuario != null){           
          //  
          
        }        
        return usuario;
    }

}
