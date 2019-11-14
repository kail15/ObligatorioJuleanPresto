package obligatorio.modelo;

import java.util.ArrayList;
import java.util.function.Consumer;
import obligatorio.exceptions.CredencialesInvalidasException;
import obligatorio.exceptions.MesaAbiertaException;
import obligatorio.exceptions.UsuarioLogueadoException;
import obligatorio.fachada.Fachada;

//OJP
public class SistemaUsuarios {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Usuario> usuariosLogueados;

    public SistemaUsuarios() {
        usuarios = new ArrayList();
        usuariosLogueados = new ArrayList();
    }

    public ArrayList<Usuario> getUsuariosLogueados() {
        return usuariosLogueados;
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

    public void transferirMesa(MesaTransferida mesa) {
        Fachada.getInstancia().notificar(Fachada.EVENTOS.TRANSFERIR_MESA);
    }

    public void elimiarMesa(MesaTransferida mesa) {
        this.usuariosLogueados.forEach((u) -> {
            Mesa m = new Mesa(mesa.getNumero());
            u.elimiarMesa(m);
        });

        // CORREGIR
        /*this.usuarios.forEach((u) -> {            
           Mesa m = new Mesa(mesa.getNumero());
           u.elimiarMesa(m);
        });*/
    }

    public void agregarMesa(MesaTransferida mesa) {
        this.usuariosLogueados.forEach((Usuario u) -> {
            if (u.getUserId() == null ? mesa.getMozoDestino() == null : u.getUserId().equals(mesa.getMozoDestino())) {
                Mesa m = new Mesa(mesa.getNumero(), mesa.isEstadoMesa());
                u.agregarMesa(m);
            }

        });

        this.usuarios.forEach((u) -> {
            if (u.getUserId() == mesa.getMozoDestino()) {
                Mesa m = new Mesa(mesa.getNumero());
                u.agregarMesa(m);
            }
        });
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

    public void confirmarUnidad(UnidadProcesadora unidad, String userId) {
        this.usuariosLogueados.forEach((u) -> {
            if (userId.equals(u.getUserId())) {
                u.setUnidadProcesadora(unidad);
            }
        });
    }

    public boolean logout(Mozo mozo) throws MesaAbiertaException {
        Usuario usuarioMozo = UsuarioById(mozo.getUserId());
        boolean mesaAbierta = false;
        for (Mesa m : usuarioMozo.obtenerMesas()) {
            if (m.getEstado()) {
                mesaAbierta = true;
                break;
            }
        }
        if (!mesaAbierta) {
            this.usuariosLogueados.removeIf(m -> (m.getUserId().equals(mozo.getUserId())));
            return true;
        } else {
            throw new MesaAbiertaException("Aun tienes mesas abiertas");
        }
    }
}
