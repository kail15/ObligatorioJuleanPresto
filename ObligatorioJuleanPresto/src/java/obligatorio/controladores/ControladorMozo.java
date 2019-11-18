package obligatorio.controladores;

import java.util.List;
import obligatorio.exceptions.ClienteException;
import obligatorio.exceptions.MesaException;
import obligatorio.exceptions.PedidoException;
import obligatorio.fachada.Fachada;
import obligatorio.vista.web.utils.EventoMensaje;
import obligatorio.modelo.Mesa;
import obligatorio.modelo.MesaTransferida;
import obligatorio.modelo.Mozo;
import obligatorio.modelo.Pedido;
import obligatorio.modelo.Producto;
import obligatorio.modelo.Servicio;
import obligatorio.modelo.Usuario;
import obligatorio.vista.web.dto.MesaDTO;
import obligatorio.vista.web.dto.ServicioDTO;
import obligatorio.vista.web.dto.UnidadProcesadoraDTO;
import obligatorio.vista.web.utils.NotificarHelper;
import observer.Observable;
import observer.Observador;

public class ControladorMozo implements Observador {

    private Fachada fachada;
    private VistaMozo vista;
    private Usuario mozoLogueado;

    public ControladorMozo(VistaMozo vista, Usuario mozo) {
        this.vista = vista;
        this.fachada = Fachada.getInstancia();
        this.fachada.agregar(this);
        this.mozoLogueado = mozo;
        this.vista.obtenerMozo(mozo.getUserId());
        this.vista.obtenerProductos(this.fachada.obtenerProductos());
        this.vista.obtenerMozosLogueados(this.fachada.getInstancia().obtenerUsuariosLogueados());
    }

    public Usuario getMozoLogueado() {
        return mozoLogueado;
    }

    public void setMozoLogueado(Usuario mozoLogueado) {
        this.mozoLogueado = mozoLogueado;
    }

    public List<Mesa> CambiarEstadoMesa(MesaDTO mesaDto) {
        try {
            List<Mesa> mesasMozo;
            Mesa mesa = new Mesa(mesaDto.getNumero(), mesaDto.getEstado());
            this.fachada.cambiarEstadoMesa(mesa);
            mesasMozo = this.mozoLogueado.CambiarEstadoMesa(mesa.getNumero(), mesa.getEstado());

            return mesasMozo;
        } catch (MesaException ex) {
            vista.mostrarError(ex.getMessage());
        }
        return null;
    }

    public void logout(Mozo mozo) {
        try {
            this.fachada.logout(mozo);
        } catch (MesaException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }

    public void transferirMesa(MesaTransferida mesa) {
        Fachada.getInstancia().transferirMesa(mesa);
    }

    public void aceptarMesaTransf(MesaTransferida mesa) {
        Fachada.getInstancia().aceptarMesaTransf(mesa);
    }

    public void agregarPedido(Pedido pedido) {
        try {
            this.fachada.agregarPedido(pedido);
        } catch (PedidoException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }

    public Usuario usuarioById(String usuarioId) {
        return Fachada.getInstancia().usuarioById(usuarioId);
    }

    public void confirmarServicio(Usuario mozo, Mesa mesaServ) {
        try {
            this.fachada.confirmarServicio(mozo, mesaServ);
        } catch (ClienteException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }

    @Override
    public void actualizar(Object evento, Observable origen) {

        if (evento instanceof NotificarHelper) {
            NotificarHelper ret = (NotificarHelper) evento;
            if (ret.getEvento().equals(EventoMensaje.LOGOUT)) {
                List<Usuario> usuarios = this.fachada.obtenerUsuariosLogueados();
                vista.obtenerMozosLogueados(usuarios);
                Usuario usuarioLogout = (Usuario) ((NotificarHelper) evento).getObjetoNotificar();
                vista.logoutMozo(usuarioLogout);
            }
            if (ret.getEvento().equals(EventoMensaje.TRANSFERIR_MESA)) {
                MesaTransferida mesaT = (MesaTransferida) ret.getObjetoNotificar();
                vista.transferirMesa(mesaT);
            }

            if (ret.getEvento().equals(EventoMensaje.ACEPTAR_MESA)) {
                MesaTransferida mesaT = (MesaTransferida) ret.getObjetoNotificar();
                vista.aceptarMesaTransf(mesaT);
            }

            if (ret.getEvento().equals(EventoMensaje.CAMBIO_ESTADO_PEDIDO)) {
                vista.obtenerMozo(null);
            }
            if (ret.getEvento().equals(EventoMensaje.OBTENER_PEDIDOS)) {
                List<Producto> productos = this.fachada.obtenerProductos();
                vista.obtenerProductos(productos);
                vista.obtenerMozo(null);
            }
            if (ret.getEvento().equals(EventoMensaje.LIMPIAR_SERVICIO)) {
                ServicioDTO servicio = (ServicioDTO) ret.getObjetoNotificar();
                vista.devolverServicio(servicio);
            }
            if (ret.getEvento().equals(EventoMensaje.NUEVO_USUARIO_LOGUEADO)) {
                List<Usuario> usuarios = Fachada.getInstancia().obtenerUsuariosLogueados();
                vista.obtenerMozosLogueados(usuarios);
            }

        }
    }
}
