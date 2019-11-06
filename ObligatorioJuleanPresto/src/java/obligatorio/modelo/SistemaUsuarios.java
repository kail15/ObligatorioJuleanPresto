package obligatorio.modelo;

import java.util.ArrayList;
import obligatorio.exceptions.CredencialesInvalidasException;
import obligatorio.exceptions.UsuarioInactivoException;

//OJP
public class SistemaUsuarios {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Usuario> usuariosLogueados;

    public SistemaUsuarios() {
        usuarios = new ArrayList();
    }

    public Usuario login(String n, String p) throws CredencialesInvalidasException, UsuarioInactivoException {
        Usuario usuarioLogueado = null;
        for (Usuario user : usuarios) {
            if (user.validarLogin(n, p)) {
                usuarioLogueado = user;
                break;
            }
        }
        if (usuarioLogueado == null) {
            throw new CredencialesInvalidasException("Credenciales invalidas");
        } else {
            return usuarioLogueado;
        }
    }

    public void agregarUsuario(Usuario u) {
        usuarios.add(u);
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
