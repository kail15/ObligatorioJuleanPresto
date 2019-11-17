package obligatorio.modelo;

import obligatorio.vista.web.utils.EstadoPedido;
import java.util.ArrayList;
import java.util.List;
import obligatorio.exceptions.MesaException;
import obligatorio.exceptions.PedidoException;
import obligatorio.vista.web.dto.MesaDTO;

public class SistemaPedidos {

    private ArrayList<Pedido> pedidos;

    public SistemaPedidos() {
        this.pedidos = new ArrayList();
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<Pedido> getPedidosEnEspera(UnidadProcesadora unidad) {
        ArrayList<Pedido> pedidosEnEspera = new ArrayList();
        pedidos.forEach((p) -> {
            Producto prod = p.getProducto();
            if (p.getProducto().validarUnidadProcesadora(unidad) && p.getEstado().equals(EstadoPedido.EN_ESPERA)) {
                pedidosEnEspera.add(p);
            }
        });
        return pedidosEnEspera;
    }
    
    public List<Pedido> getPedidosByUnidad(UnidadProcesadora unidad) {
        
        ArrayList<Pedido> pedidosEnEspera = new ArrayList();
        pedidos.forEach((p) -> {
            Producto prod = p.getProducto();
            if (p.getProducto().validarUnidadProcesadora(unidad)) {
                pedidosEnEspera.add(p);
            }
        });
        return pedidosEnEspera;
    }

    public void agregarPedido(Pedido pedido) throws PedidoException {
        boolean hayStock = pedido.getProducto().validarProductoStock(pedido);

        if (!hayStock) {
            throw new PedidoException("sin stock");
        } else if (pedido.getCantidad() < 1) {
            throw new PedidoException("cantidad inválida");
        } else {
            this.pedidos.add(pedido);
        }
    }

    public void cambiarEstadoPedido(Pedido unPedido) {
        pedidos.forEach((p) -> {
            if (p.getPedidoId() == unPedido.getPedidoId()) {
                p.setEstado(unPedido.getEstado());
                p.setGestor(unPedido.getGestor());
            }
        });
    }

    public boolean validarMesasConPedido(Mesa mesa) throws MesaException {

        boolean cierra = false;
        for (Pedido p : this.pedidos) {
            if (p.getMesa().getNumero() == mesa.getNumero()) {
                cierra = p.validarMesasConPedido(mesa);
                break;
            }
        }
        if (!cierra) {
            return true;
        } else {
            throw new MesaException("La mesa tienen pedidos pendientes");
        }
    }

    public void cargarPedido(Pedido p) {
        this.pedidos.add(p);
    }    

}
