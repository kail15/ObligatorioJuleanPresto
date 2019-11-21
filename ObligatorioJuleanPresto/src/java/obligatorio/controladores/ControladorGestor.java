package obligatorio.controladores;

import java.util.List;
import obligatorio.fachada.Fachada;
import obligatorio.modelo.Pedido;
import obligatorio.modelo.UnidadProcesadora;
import obligatorio.modelo.Usuario;
import obligatorio.vista.web.dto.PedidoDTO;
import obligatorio.vista.web.dto.UnidadProcesadoraDTO;
import obligatorio.vista.web.utils.EventoMensaje;
import obligatorio.vista.web.utils.NotificarHelper;
import observer.Observable;
import observer.Observador;

public class ControladorGestor implements Observador {

    private Fachada fachada;
    private VistaGestor vista;
    private Usuario gestorLogueado;

    public ControladorGestor(VistaGestor vista, Usuario gestor) {
        this.fachada = Fachada.getInstancia();
        this.fachada.agregar(this);
        this.vista = vista;
        this.gestorLogueado = gestor;
        this.vista.mostrarUnidades(this.fachada.obtenerUnidades());
    }

    public void confirmarUnidad(UnidadProcesadora unidad, String userId) {
        this.fachada.confirmarUnidad(unidad, userId);
        vista.cargarUnidad();
    }

    @Override
    public void actualizar(Object evento, Observable origen) {

        if (evento instanceof NotificarHelper) {
            NotificarHelper helper = (NotificarHelper) evento;
            if (helper.getEvento().equals(EventoMensaje.PEDIDO_PROCESADO)) {
                List<Pedido> pedidos = this.fachada.getPedidos();
                vista.obtenerPedidosTotales(pedidos);
            }
            if (helper.getEvento().equals(EventoMensaje.OBTENER_PEDIDOS)) {
                UnidadProcesadoraDTO unidad = (UnidadProcesadoraDTO) helper.getObjetoNotificar();
                UnidadProcesadora unidadModel = new UnidadProcesadora(unidad.getId(), unidad.getNombre());
                List<Pedido> pedidos = this.fachada.getPedidos();
                vista.obtenerPedidosTotales(pedidos);
            }
            if(helper.getEvento().equals(EventoMensaje.ENVIAR_PEDIDO)){
                UnidadProcesadora unidad = (UnidadProcesadora) helper.getObjetoNotificar();
                List<Pedido> pedidos = this.fachada.getPedidos();
                vista.obtenerPedidosTotales(pedidos);           
            }
//            if(helper.getEvento().equals(EventoMensaje.ENVIAR_PEDIDO)){
//                UnidadProcesadora unidad = (UnidadProcesadora) helper.getObjetoNotificar();
//                List<Pedido> pedidos = this.fachada.getPedidos();
//                vista.obtenerPedidosTotales(pedidos);           
//            }
            if (helper.getEvento().equals(EventoMensaje.LIMPIAR_PEDIDOS)) {
                List<Pedido> pedidos = this.fachada.getPedidos();
                vista.obtenerPedidosTotales(pedidos);
            }
            
        }
    }

    public void cargarPedidos(UnidadProcesadoraDTO unidadPedido) {
        this.fachada.devolverPedidosEnEspera(unidadPedido);
    }

    public void cambiarEstadoPedido(PedidoDTO pedidoDto) {
        this.fachada.cambiarEstadoPedido(pedidoDto);
    }

}
