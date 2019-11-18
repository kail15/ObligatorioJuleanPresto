/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import obligatorio.modelo.UnidadProcesadora;

/**
 *
 * @author Usuario
 */
public class MapeadorUnidadProcesadora implements Mapeador{
    private UnidadProcesadora unidadProcesadora;

    public MapeadorUnidadProcesadora() {
    }

    public MapeadorUnidadProcesadora(UnidadProcesadora unidadProcesadora) {
        this.unidadProcesadora = unidadProcesadora;
    }

    @Override
    public int getOid() {
        return unidadProcesadora.getOid();
    }

    @Override
    public void setOid(int oid) {
        unidadProcesadora.setOid(oid);
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
        return "SELECT * FROM unidades";
    }

    @Override
    public void crearNuevo() {
        unidadProcesadora = new UnidadProcesadora();
    }

    @Override
    public Object getObjeto() {
        return unidadProcesadora;
    }

    @Override
    public void leerCompuesto(ResultSet rs) throws SQLException {
        unidadProcesadora.setNombre(rs.getString("nombre"));
    }

    @Override
    public void leerComponente(ResultSet rs) throws SQLException {

    }
    
}
