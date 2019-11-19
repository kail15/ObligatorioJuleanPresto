/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import obligatorio.modelo.Mesa;
import obligatorio.modelo.Usuario;

/**
 *
 * @author Usuario
 */
public class MapeadorUsuario implements Mapeador {

    private Usuario usuario;

    public MapeadorUsuario() {
    }

    public MapeadorUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int getOid() {
        return usuario.getOid();
    }

    @Override
    public void setOid(int oid) {
        usuario.setOid(oid);
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
        return "SELECT * FROM usuarios";
    }

    @Override
    public void crearNuevo() {
       usuario = new Usuario() {
           @Override
           public void agregarMesa(Mesa mesa) {
               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
           
       };
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getObjeto() {
        return usuario;
    }

    @Override
    public void leerCompuesto(ResultSet rs) throws SQLException {
        //int oId = rs.getInt("oid");
        String userId = rs.getString("oid");
        String userName = rs.getString("nombreUsuario");
        String userPassword = rs.getString("password");
        String nombreCompleto = rs.getString("nombreCompleto");
        String tipoUsuario = rs.getString("tipoUsuario");
        
        // usuario.setOid(roId);
        usuario.setUserId(userId);
        usuario.setNombreUsuario(userName);
        usuario.setPassword(userPassword);
        usuario.setNombreCompleto(nombreCompleto);
        usuario.setTipoUsuario(tipoUsuario);
    }

    @Override
    public void leerComponente(ResultSet rs) throws SQLException {
        /*MapeadorMesa mapeadorMesa = new MapeadorMesa();
        
        if(rs.getString("tipoUsuario").equals("mozo")){
            usuario.obtenerMesas().add(new Mesa(rs.getInt("numero"),
                    mapeadorMesa.convertirEstado(rs.getInt("estado"))));
        }
        else{
            usuario.setUnidadProcesadora(null);
        }*/
    }

}
