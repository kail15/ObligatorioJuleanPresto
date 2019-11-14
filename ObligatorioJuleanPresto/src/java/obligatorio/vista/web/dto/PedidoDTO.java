package obligatorio.vista.web.dto;

import obligatorio.vista.web.utils.EstadoPedido;

public class PedidoDTO {

    private int pedidoId;
    private int producto;
    private String nombreProducto;
    private int cantidad;
    private String descripcion;
    private int mesa;
    private String mozoId;
    private String mozoNombre;
    private String gestorId;
    private EstadoPedido estado;

    public PedidoDTO(int producto, String nombreProducto, int cantidad, String descripcion, int mesa, String mozoId, String mozoNombre) {
        this.producto = producto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.mesa = mesa;
        this.mozoId = mozoId;
        this.mozoNombre = mozoNombre;
    }

    public PedidoDTO(int producto, String nombreProducto, int cantidad, String descripcion, int mesa, String mozoId, String mozoNombre, String gestorId) {
        this.producto = producto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.mesa = mesa;
        this.mozoId = mozoId;
        this.mozoNombre = mozoNombre;
        this.gestorId = gestorId;
    }

    public PedidoDTO(int pedidoId, int producto, String nombreProducto, int cantidad, String descripcion, int mesa, String mozoId, String mozoNombre) {
        this.producto = producto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.mesa = mesa;
        this.mozoId = mozoId;
        this.mozoNombre = mozoNombre;
        this.gestorId = gestorId;
        this.pedidoId = pedidoId;
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

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getGestorId() {
        return gestorId;
    }

    public void setGestorId(String gestorId) {
        this.gestorId = gestorId;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }    

}
