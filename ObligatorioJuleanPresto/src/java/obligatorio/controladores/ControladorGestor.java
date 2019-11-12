package obligatorio.controladores;

import java.util.List;
import obligatorio.fachada.Fachada;
import obligatorio.modelo.Pedido;
import obligatorio.modelo.UnidadProcesadora;
import obligatorio.modelo.Usuario;
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
    }

    public void confirmarUnidad(UnidadProcesadora unidad, String userId) {
        this.fachada.confirmarUnidad(unidad, userId);
        vista.cargarUnidad();
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
        if (evento.equals(Fachada.EVENTOS.PEDIDOS_EN_ESPERA)) {
            List<Pedido> pedidos = this.fachada.getPedidosEnEspera();
             vista.obtenerPedidos(pedidos);
        }

    }

}
