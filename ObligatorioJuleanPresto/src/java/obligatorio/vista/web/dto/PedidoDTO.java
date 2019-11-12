/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.vista.web.dto;

/**
 *
 * @author Usuario
 */
public class PedidoDTO {
    private int producto;
    private int cantidad;
    private String descripcion;
    private int mesa;
    private String mozoId;
    private String mozoNombre;      

    public PedidoDTO(int producto, int cantidad, String descripcion, int mesa, String mozoId, String mozoNombre) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.mesa = mesa;
        this.mozoId = mozoId;
        this.mozoNombre = mozoNombre;
    }

    public int getProducto() {
        return producto;
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
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public String getMozoId() {
        return mozoId;
    }

    public void setMozoId(String mozoId) {
        this.mozoId = mozoId;
    }

    public String getMozoNombre() {
        return mozoNombre;
    }

    public void setMozoNombre(String mozoNombre) {
        this.mozoNombre = mozoNombre;
    }
    
    
    
    
    
    
    
}
