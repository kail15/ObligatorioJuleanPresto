package obligatorio.vista.web.dto;

import java.util.ArrayList;
import java.util.List;

public class ServicioDTO {

    private List<PedidoDTO> pedidos = new ArrayList<>();
    private double costoServicio;
    private double descuentoServicio;
    private double totalApagar;
    private String beneficioNombre;
    private String nombreCliente;

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

    public double getCostoServicio() {
        return costoServicio;
    }

    public void setCostoServicio(double costoServicio) {
        this.costoServicio = costoServicio;
    }

    public double getDescuentoServicio() {
        return descuentoServicio;
    }

    public void setDescuentoServicio(double descuentoServicio) {
        this.descuentoServicio = descuentoServicio;
    }

    public double getTotalApagar() {
        return totalApagar;
    }

    public void setTotalApagar(double totalApagar) {
        this.totalApagar = totalApagar;
    }

    public String getBeneficioNombre() {
        return beneficioNombre;
    }

    public void setBeneficioNombre(String beneficioNombre) {
        this.beneficioNombre = beneficioNombre;
    }   

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }    

}
