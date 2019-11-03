package obligatorio.fachada;

import java.util.Observable;
import obligatorio.exceptions.CredencialesInvalidasException;
import obligatorio.exceptions.UsuarioInactivoException;
import obligatorio.modelo.Gestor;
import obligatorio.modelo.Mesa;
import obligatorio.modelo.Mozo;
import obligatorio.modelo.SistemaUsuarios;
import obligatorio.modelo.Usuario;

//OJP
public class Fachada extends Observable {

    private SistemaUsuarios sistemaUsuarios;
    private static Fachada instancia;

    private Fachada() {
        sistemaUsuarios = new SistemaUsuarios();
        cargar();
    }

    public static Fachada getInstancia() {
        //Implementacion de singleton 
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    public Usuario login(String n, String p) throws CredencialesInvalidasException, UsuarioInactivoException {

        Usuario usuario = sistemaUsuarios.login(n, p);
        if (usuario != null) {
            //  

        }
        return usuario;
    }

    public void agregarUsuario(Usuario u) {
        sistemaUsuarios.agregarUsuario(u);
    }

    public void cargar() {
        
        Usuario carlos = new Mozo("1" ,"user1", "pass1", "carlos valdez");
        Usuario juan = new Gestor("2" ,"user2", "pass2", "juan perez");
        Mesa mesa1 = new Mesa(1);
        
        carlos.agregarMesa(mesa1);

        this.agregarUsuario(carlos);
        this.agregarUsuario(juan);
    }

}
