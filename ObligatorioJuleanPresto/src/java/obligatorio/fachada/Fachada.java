package obligatorio.fachada;

import java.util.Observable;
import obligatorio.exceptions.CredencialesInvalidasException;
import obligatorio.exceptions.UsuarioInactivoException;
import obligatorio.exceptions.UsuarioLogueadoException;
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

    public Usuario login(String n, String p) throws CredencialesInvalidasException, UsuarioInactivoException, UsuarioLogueadoException {

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
        
        Usuario carlos = new Mozo("4" ,"user1", "pass1", "carlos valdez");
        Usuario pedro = new Mozo("5" ,"user3", "pass3", "pedro mendez");
        Usuario juan = new Gestor("2" ,"user2", "pass2", "juan perez");
        Mesa mesa1 = new Mesa(1);
        Mesa mesa2 = new Mesa(2);
        Mesa mesa3 = new Mesa(3);
        mesa3.setEstado(true);
        mesa2.setEstado(true);
        Mesa mesa4 = new Mesa(4);
        
        Mesa mesa5 = new Mesa(5);
        Mesa mesa6 = new Mesa(6);
        
        carlos.agregarMesa(mesa1);
        carlos.agregarMesa(mesa2);
        carlos.agregarMesa(mesa3);
        carlos.agregarMesa(mesa4);
        
        carlos.agregarMesa(mesa5);
        carlos.agregarMesa(mesa6);

        this.agregarUsuario(carlos);
        this.agregarUsuario(pedro);
        this.agregarUsuario(juan);
    }

}
