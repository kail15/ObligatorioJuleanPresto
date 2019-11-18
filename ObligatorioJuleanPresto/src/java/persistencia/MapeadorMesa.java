/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import obligatorio.fachada.Fachada;
import obligatorio.modelo.Mesa;


public class MapeadorMesa implements Mapeador {

    private Mesa mesa;

    public MapeadorMesa() {
    }

    public MapeadorMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public int getOid() {
        return mesa.getOid();
    }

    @Override
    public void setOid(int oid) {
        mesa.setOid(oid);
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
        return "SELECT * FROM mesas";
    }

    @Override
    public void crearNuevo() {
        mesa = new Mesa();
    }

    @Override
    public Object getObjeto() {
        return mesa;
    }

    @Override
    public void leerCompuesto(ResultSet rs) throws SQLException {
        String mozoId = rs.getString("mozo_oid");
        
        mesa.setOid(Integer.parseInt(rs.getString("oid")));
        mesa.setNumero(Integer.parseInt(rs.getString("numero")));
        mesa.setEstado(convertirEstado(rs.getInt("estado")));
        mesa.setMozoId(mozoId);
    }

    @Override
    public void leerComponente(ResultSet rs) throws SQLException {
       // fachada.asignarMesa(rs.getInt("mozo_oid"),this.mesa);
    }

    public boolean convertirEstado(int estado) {
        return estado == 1;
    }

}
