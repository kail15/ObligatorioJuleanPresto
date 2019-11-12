package obligatorio.controladores;

import java.util.List;
import obligatorio.fachada.Fachada;
import obligatorio.modelo.EventoMensaje;
import obligatorio.modelo.Mesa;
import obligatorio.modelo.MesaTransferida;
import obligatorio.modelo.Pedido;
import obligatorio.modelo.Producto;
import obligatorio.modelo.Usuario;
import obligatorio.vista.web.dto.WsMessageDTO.TipoMensaje;
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

    public List<Mesa> CambiarEstadoMesa(int id, boolean estado) {
        List<Mesa> mesasMozo;
        mesasMozo = this.mozoLogueado.CambiarEstadoMesa(id, estado);
        return mesasMozo;
    }

    public void transferirMesa(MesaTransferida mesa) {
        Fachada.getInstancia().transferirMesa(mesa);
    }

    public void aceptarMesaTransf(MesaTransferida mesa) {
        Fachada.getInstancia().aceptarMesaTransf(mesa);
    }

    public void agregarPedido(Pedido pedido) {
        this.fachada.agregarPedido(pedido);
    }

    public Usuario usuarioById(String usuarioId) {
        return Fachada.getInstancia().usuarioById(usuarioId);
    }

    @Override
    public void actualizar(Object evento, Observable origen) {

        if (evento instanceof MesaTransferida) {
            MesaTransferida mesaT = (MesaTransferida) evento;
            if (mesaT.getTipoMensaje().equals(EventoMensaje.TRANSFERIR_MESA)) {
                vista.transferirMesa(mesaT);
            } else if (mesaT.getTipoMensaje().equals(EventoMensaje.ACEPTAR_MESA)) {
                vista.aceptarMesaTransf(mesaT);
            }

        }       
        if (evento.equals(Fachada.EVENTOS.NUEVO_USUARIO_LOGUEADO)) {
            List<Usuario> usuarios = this.fachada.getInstancia().obtenerUsuariosLogueados();
            vista.obtenerMozosLogueados(usuarios);
        }       

    }

}
