package obligatorio.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import obligatorio.exceptions.CredencialesInvalidasException;
import obligatorio.exceptions.MesaException;
import obligatorio.exceptions.UsuarioLogueadoException;
import obligatorio.fachada.Fachada;
import obligatorio.vista.web.dto.MesaDTO;
import persistencia.BaseDatos;
import persistencia.MapeadorUsuario;
import persistencia.Persistencia;

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

    public void elimiarMesa(MesaTransferida mesa) {
        /* this.usuariosLogueados.forEach((u) -> {
            Mesa m = new Mesa(mesa.getNumero());
            u.elimiarMesa(m);
        });*/

        Usuario mozoOrigen = UsuarioById(mesa.getMozoOrigen());
        Mesa m = new Mesa(mesa.getNumero());
        mozoOrigen.elimiarMesa(m);

        int test = 0;

        // CORREGIR
        /*this.usuarios.forEach((u) -> {            
           Mesa m = new Mesa(mesa.getNumero());
           u.elimiarMesa(m);
        });*/
    }

    public void agregarMesa(MesaTransferida mesa) {
        this.usuariosLogueados.forEach((Usuario u) -> {
            if (u.getUserId() == null ? mesa.getMozoDestino() == null : u.getUserId().equals(mesa.getMozoDestino())) {

                Usuario usu = UsuarioById(mesa.getMozoOrigen());
                Mesa m = usu.obtenerMesaByNumero(mesa.getNumero());
                //Mesa m = new Mesa(mesa.getNumero(), mesa.isEstadoMesa());
                u.agregarMesa(m);
            }

        });

//        this.usuarios.forEach((u) -> {
//            if (u.getUserId() == mesa.getMozoDestino()) {
//                Mesa m = new Mesa(mesa.getNumero());
//                u.agregarMesa(m);
//            }
//        });
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

    public boolean logout(Mozo mozo) throws MesaException {
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
            throw new MesaException("Aun tienes mesas abiertas");
        }
    }

    public void confirmarServicio(Usuario mozo, Mesa mesaServ) {
        for (Usuario usu : this.usuariosLogueados) {
            if (usu.getUserId().equals(mozo.getUserId())) {
                usu.limpiarServicioMesa(mesaServ);
                break;
            }
        }
    }

    public void cargarDatos() {
        this.conectarBD();
        this.cargarUsuarios();
        System.out.println("DATOS CARGADOS");
    }

    public void asignarMesa(int oidMozo, Mesa mesa) {
        Usuario usuario = UsuarioById(Integer.toString(oidMozo));
        if (usuario != null) {
            usuario.obtenerMesas().add(mesa);
        }
    }

    private void conectarBD() {
        String url = "jdbc:mysql://localhost:3306/obligatoriojuleanpresto";
        BaseDatos bd = BaseDatos.getInstancia();
        bd.conectarse("com.mysql.jdbc.Driver", url, "root", "root");
    }

    private void cargarUsuarios() {
        usuarios = Persistencia.getInstancia().obtenerTodos(new MapeadorUsuario());
        normalizarUsuarios();
    }

    private void normalizarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList();
        for (Usuario u : this.usuarios) {
            if (u.getTipoUsuario().equals("mozo")) {
                Usuario mozo = new Mozo(u.getUserId(), u.getNombreUsuario(), u.getPassword(), u.getNombreCompleto());
                usuarios.add(mozo);
            }
            if (u.getTipoUsuario().equals("gestor")) {
                Usuario gestor = new Gestor(u.getUserId(), u.getNombreUsuario(), u.getPassword(), u.getNombreCompleto());
                usuarios.add(gestor);
            }
        }
        this.usuarios = usuarios;
    }

}
