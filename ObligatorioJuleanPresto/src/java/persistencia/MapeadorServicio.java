/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import obligatorio.fachada.Fachada;
import obligatorio.modelo.Item;
import obligatorio.modelo.Producto;
import obligatorio.modelo.Servicio;

/**
 *
 * @author Usuario
 */
public class MapeadorServicio implements Mapeador {

    private Servicio servicio;

    public MapeadorServicio() {
    }

    public MapeadorServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    @Override
    public int getOid() {
        return servicio.getOid();
    }

    @Override
    public void setOid(int oid) {
        servicio.setOid(oid);
    }

    @Override
    public ArrayList<String> getSqlInsertar() {
        ArrayList<String> sqls = new ArrayList();
        sqls.add(
                "insert into servicios values(" + servicio.getOid() + ","
                + servicio.getCliente().getId()+ ")"
        );
        return sqls;
    }

    @Override
    public ArrayList<String> getSqlActualizar() {
        ArrayList<String> sqls = new ArrayList();
        sqls.add(
                "delete from items where servicio_oid = " + servicio.getOid()
        );
        generarItems(sqls);
        return sqls;
    }

    @Override
    public ArrayList<String> getSqlBorrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSqlSeleccionar() {
        return "SELECT * FROM servicios s,items i WHERE s.oid=i.servicio_oid AND s.cliente_oid = "
                + servicio.getCliente().getId();
    }

    @Override
    public void crearNuevo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getObjeto() {
        return servicio;
    }

    @Override
    public void leerCompuesto(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void leerComponente(ResultSet rs) throws SQLException {
        /*Producto producto = Fachada.getInstancia().buscarProductoByOid(Integer.parseInt(rs.getString("producto_oid")));
        servicio.getItems().add(new Item(
                producto,
                rs.getInt("cantidad"),
                rs.getString("descripcion"),
                Fachada.getInstancia().getEstadoItem(rs.getString("estado")))
        );*/
    }

    private void generarItems(ArrayList<String> sqls) {
        //terminar
        /*List<Item> items = servicio.getItems();
        for(Item i:items){
            sqls.add(
                    "insert into contactos values(" + agenda.getOid() + ",'" + 
                            c.getNombre() + "','" + c.getTelefono() + "'," + c.getTipo().getOid() + ")"
            );
        }*/
    }

}
