
package obligatorio.modelo;

import java.util.ArrayList;
import java.util.List;


public class Servicio {
    private List<Pedido> pedidos = new ArrayList<>();
    private Cliente cliente;

    public Servicio() {
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    } 
    
    public void agregarPedido(Pedido p){
      this.pedidos.add(p);
    }    
}
