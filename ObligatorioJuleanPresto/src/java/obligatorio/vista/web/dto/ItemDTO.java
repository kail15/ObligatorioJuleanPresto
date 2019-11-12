/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.vista.web.dto;

import obligatorio.modelo.EstadoItem;


public class ItemDTO {
    
    private ProductoDTO producto;
    private int cantidad;
    private String descripcion;
    private EstadoItem estado;

    public ItemDTO(ProductoDTO producto, int cantidad, String descripcion) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
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

    public EstadoItem getEstado() {
        return estado;
    }

    public void setEstado(EstadoItem estado) {
        this.estado = estado;
    }
    
    
    
    
    
    
}
