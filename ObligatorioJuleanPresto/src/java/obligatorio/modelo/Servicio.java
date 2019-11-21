
package obligatorio.modelo;

import java.util.ArrayList;
import java.util.List;


public class Servicio {
    private int oid;
    private List<Pedido> pedidos = new ArrayList<>();
    private Cliente cliente;
    private Mesa mesa;
    private double precioTotal;
    private double costoServicio;
    private double descuento;
    private String beneficio;

    public Servicio() {
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
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

    public void limpiarServicio() {
        this.pedidos.clear();
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public String getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(String beneficio) {
        this.beneficio = beneficio;
    } 
    
    public double calcularPrecioTotal(){
       double total = 0;
       
       for(Pedido ped : this.pedidos){
          total += ped.getPrecioProducto() * ped.getCantidad();          
       }
       
       return total;       
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }  

    public double getCostoServicio() {
        return costoServicio;
    }

    public void setCostoServicio(double costoServicio) {
        this.costoServicio = costoServicio;
    }
    
    
}
