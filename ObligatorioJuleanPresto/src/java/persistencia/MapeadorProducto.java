/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import obligatorio.modelo.Producto;
import obligatorio.modelo.UnidadProcesadora;

/**
 *
 * @author Usuario
 */
public class MapeadorProducto implements Mapeador{
    private Producto producto;

    public MapeadorProducto() {
    }

    public MapeadorProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int getOid() {
        return producto.getOid();
    }

    @Override
    public void setOid(int oid) {
        producto.setOid(oid);
    }

    @Override
    public ArrayList<String> getSqlInsertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getSqlActualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getSqlBorrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSqlSeleccionar() {
        return "SELECT * FROM productos";
    }

    @Override
    public void crearNuevo() {
        producto = new Producto();
    }

    @Override
    public Object getObjeto() {
        return producto;
    }

    @Override
    public void leerCompuesto(ResultSet rs) throws SQLException {
        
        int codigoProd = Integer.parseInt(rs.getString("codigo"));
        String nombreProd = rs.getString("nombre");        
        double precioUni = Double.parseDouble(rs.getString("precioUnitario"));
        int stockProd = Integer.parseInt(rs.getString("stock"));
        int unidadId = Integer.parseInt(rs.getString("unidad_oid")); 
        
        UnidadProcesadora unidad = new UnidadProcesadora();
        unidad.setOid(unidadId);
        
        producto.setCodigo(codigoProd);
        producto.setNombre(nombreProd);
        producto.setPrecioUnitario(precioUni);
        producto.setStockDisponible(stockProd);
        producto.setUnidadProcesadora(unidad);
    }

    @Override
    public void leerComponente(ResultSet rs) throws SQLException {
        producto.setUnidadProcesadora(new UnidadProcesadora(Integer.parseInt(rs.getString("unidad_oid")),
                rs.getString("nombre")));
    }
    
}
