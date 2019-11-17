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
public class Producto {

    private int oid;
    private int codigo;
    private String nombre;
    private double precioUnitario;
    private int stockDisponible;
    private UnidadProcesadora unidadProcesadora;

    public Producto(int codigo, String nombre, double precioUnitario, int stockDisponible, UnidadProcesadora unidadProcesadora) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.stockDisponible = stockDisponible;
        this.unidadProcesadora = unidadProcesadora;
    }

    public Producto() {
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public UnidadProcesadora getUnidadProcesadora() {
        return unidadProcesadora;
    }

    public void setUnidadProcesadora(UnidadProcesadora unidadProcesadora) {
        this.unidadProcesadora = unidadProcesadora;
    }

    public boolean validarUnidadProcesadora(UnidadProcesadora unidad) {
        return unidad.getOid() == this.unidadProcesadora.getOid();
    }

    public boolean validarProductoStock(Pedido p) {
        if (p.getCantidad() <= this.stockDisponible) {
            return true;
        }
        return false;
    }
}
