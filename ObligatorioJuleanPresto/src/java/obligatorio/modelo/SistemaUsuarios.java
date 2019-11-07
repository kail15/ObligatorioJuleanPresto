package obligatorio.modelo;

import java.util.ArrayList;
import java.util.List;
import obligatorio.exceptions.CredencialesInvalidasException;
import obligatorio.exceptions.UsuarioInactivoException;
import obligatorio.exceptions.UsuarioLogueadoException;

//OJP
public class SistemaUsuarios {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Usuario> usuariosLogueados;

    public SistemaUsuarios() {
        usuarios = new ArrayList();
        usuariosLogueados = new ArrayList(); 
    }

    public Usuario login(String n, String p) throws CredencialesInvalidasException, UsuarioLogueadoException {
        Usuario usuarioLogueado = null;
        if (buscarUsuarios(n, p, usuariosLogueados) != null) {
            throw new UsuarioLogueadoException("Ud. ya esta logueado");
        }
        usuarioLogueado = buscarUsuarios(n, p, usuarios);

        if (usuarioLogueado == null) {
            throw new CredencialesInvalidasException("Credenciales invalidas");
        } else {
            return usuarioLogueado;
        }
    }

    public void agregarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public void agregarUsuarioLogueados(Usuario u) {
        usuariosLogueados.add(u);
    }

    public Usuario buscarUsuarios(String n, String p, ArrayList<Usuario> usuariosAux) {

        Usuario usuarioLogueado = null;
        for (Usuario user : usuariosAux) {
            if (user.validarLogin(n, p)) {
                usuarioLogueado = user;
                break;
            }
        }
        return usuarioLogueado;
    }

    public Usuario UsuarioById(String id) {
        Usuario usu = null;
        for (Usuario user : usuarios) {
            if (user.validarUsuario(id)) {
                usu = user;
                break;
            }
        }
        return usu;
    }

}
