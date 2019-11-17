package obligatorio.vista.web.dto;

import java.util.ArrayList;
import java.util.List;

public class ServicioDTO {

    private List<PedidoDTO> pedidos = new ArrayList<>();
    private double costoServicio;

    public ServicioDTO() {
    }

    public List<PedidoDTO> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoDTO> pedidos) {
        this.pedidos = pedidos;
    }

    public void agregarPedido(PedidoDTO p) {
        this.pedidos.add(p);
    }

    public double calcularPrecioTotal() {
        double precioTotal = 0;
        for (PedidoDTO p : this.pedidos) {
            precioTotal += (p.getPrecioProducto() * p.getCantidad());
        }
        return precioTotal;
    }

    void setPrecioServicio(double total) {
        this.costoServicio = total;
    }

}
