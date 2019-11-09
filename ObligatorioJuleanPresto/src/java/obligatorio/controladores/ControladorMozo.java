package obligatorio.controladores;

import java.util.ArrayList;
import java.util.List;
import obligatorio.fachada.Fachada;
import obligatorio.modelo.Mesa;
import obligatorio.modelo.Mozo;
import obligatorio.modelo.Producto;
import obligatorio.modelo.Usuario;
import observer.Observable;
import observer.Observador;

public class ControladorMozo implements Observador {

    private Fachada fachada;
    private VistaMozo vista;
    private Usuario mozoLogueado;

    public ControladorMozo(VistaMozo vista, Usuario mozo) {
        this.fachada = Fachada.getInstancia();
        this.vista = vista;
        this.mozoLogueado = mozo;
        this.vista.obtenerMozo(mozo.getUserId());
        this.vista.obtenerProductos(this.fachada.obtenerProductos());
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

    public void transferirMesa() {

    }

    public List<Producto> agregarPedido(Producto prod) {
        return null;
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
        if (evento.equals(Fachada.EVENTOS.TRANSFERIR_MESA)) {
            // Mesa mesaTransf = vista.obtenerMesaTransferida(0);
             //vista.transferirMesa(0);
        }

    }

}
