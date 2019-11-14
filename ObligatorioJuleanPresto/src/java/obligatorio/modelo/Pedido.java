/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.modelo;

/**
 *
 * @author Usuario
 */
public class Pedido {
    private int pedidoId;
    private static int ultPedidoId; 
    private int producto;
    private String nombreProducto;
    private int cantidad;
    private String descripcion;
    private int mesaNumero;
    private String mozo;
    private String nombreMozo;
    private EstadoPedido estado;
    private Usuario gestor;

    public Pedido(int producto, int cantidad, String descripcion, int mesaNumero, String mozo, String nombreMozo, EstadoPedido estado) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.mesaNumero = mesaNumero;
        this.mozo = mozo;
        this.nombreMozo = nombreMozo;
        this.estado = estado;
        this.pedidoId = ultPedidoId;
        ultPedidoId++;
    }
    
    public Pedido(int producto, int cantidad, String descripcion, int mesaNumero, String mozo, String nombreMozo) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.mesaNumero = mesaNumero;
        this.mozo = mozo;
        this.estado = EstadoPedido.EN_ESPERA;
        this.pedidoId = ultPedidoId;
        ultPedidoId++;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getProducto() {
        return producto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setProducto(int producto) {
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

    public int getMesa() {
        return mesaNumero;
    }

    public void setMesa(int mesa) {
        this.mesaNumero = mesa;
    }

    public String getMozo() {
        return mozo;
    }

    public void setMozo(String mozo) {
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

    public String getNombreMozo() {
        return nombreMozo;
    }

    public void setNombreMozo(String nombreMozo) {
        this.nombreMozo = nombreMozo;
    }
    
    
    
    
}
