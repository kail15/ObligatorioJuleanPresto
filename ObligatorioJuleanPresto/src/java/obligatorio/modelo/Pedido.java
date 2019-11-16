
package obligatorio.modelo;

import obligatorio.vista.web.utils.EstadoPedido;


public class Pedido {
    private int pedidoId;
    private static int ultId;
    private Producto producto;
    private int cantidad;
    private String descripcion;
    private Mesa mesa;
    private Usuario mozo;
    private EstadoPedido estado;
    private Usuario gestor;

    public Pedido(int pedidoId, Producto producto, int cantidad, String descripcion, Mesa mesa, Usuario mozo, EstadoPedido estado) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.mesa = mesa;
        this.mozo = mozo;
        this.estado = estado;        
        this.pedidoId = pedidoId;
    }
    
    public Pedido(Producto producto, int cantidad, String descripcion, Mesa mesa, Usuario mozo) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.mesa = mesa;
        this.mozo = mozo;
        ultId++;
        this.pedidoId = ultId;
        this.estado = EstadoPedido.EN_ESPERA;
    }
    
    public Pedido(){}

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Usuario getMozo() {
        return mozo;
    }

    public void setMozo(Mozo mozo) {
        this.mozo = mozo;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public void setGestor(Usuario gestor) {
        this.gestor = gestor;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Usuario getGestor() {
        return gestor;
    }    
    
    
    public boolean validarMesasConPedido(Mesa mesa){
       if (this.mesa.getNumero() == mesa.getNumero() && (this.estado.equals(EstadoPedido.EN_ESPERA) || this.estado.equals(EstadoPedido.PROCESADO))){
                return true;
       }
        return false;
    }   
}
