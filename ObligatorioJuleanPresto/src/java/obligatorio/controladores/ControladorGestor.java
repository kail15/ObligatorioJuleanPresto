package obligatorio.controladores;

import java.util.List;
import obligatorio.fachada.Fachada;
import obligatorio.modelo.Pedido;
import obligatorio.modelo.UnidadProcesadora;
import obligatorio.modelo.Usuario;
import obligatorio.vista.web.dto.PedidoDTO;
import obligatorio.vista.web.dto.UnidadProcesadoraDTO;
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

        if (evento instanceof UnidadProcesadoraDTO) {
            UnidadProcesadoraDTO unidad = (UnidadProcesadoraDTO) evento;
            UnidadProcesadora unidadModel = new UnidadProcesadora(unidad.getId(), unidad.getNombre());
            List<Pedido> pedidos = this.fachada.obtenerPedidosEnEspera(unidadModel);
            vista.obtenerPedidos(pedidos);
        }
        
        if(evento instanceof NotificarHelper){
            NotificarHelper helper = (NotificarHelper)evento;
            if(){
                
            }
        }

    }

    public void cargarPedidos(UnidadProcesadoraDTO unidadPedido) {
        this.fachada.devolverPedidosEnEspera(unidadPedido);
    }

    public void procesarPedido(PedidoDTO pedidoDto) {
        this.fachada.procesarPedido(pedidoDto);
    }

}
