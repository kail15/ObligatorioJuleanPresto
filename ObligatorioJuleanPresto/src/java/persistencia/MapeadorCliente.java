/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import obligatorio.modelo.BeneficioCliente;
import obligatorio.modelo.Cliente;
import obligatorio.modelo.Comun;
import obligatorio.modelo.DeLaCasa;
import obligatorio.modelo.Preferencial;

/**
 *
 * @author Usuario
 */
public class MapeadorCliente implements Mapeador {

    private Cliente cliente;

    public MapeadorCliente() {
    }

    public MapeadorCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int getOid() {
        return cliente.getId();
    }

    @Override
    public void setOid(int oid) {
        cliente.setId(oid);
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
        return "SELECT * FROM clientes";
    }

    @Override
    public void crearNuevo() {
        cliente = new Cliente();
    }

    @Override
    public Object getObjeto() {
        return cliente;
    }

    @Override
    public void leerCompuesto(ResultSet rs) throws SQLException {

        String nombreCliente = rs.getString("nombre");
        int beneficioCliente = Integer.parseInt(rs.getString("beneficio"));
        String email = rs.getString("email");
        BeneficioCliente beneficioCli = null;

        switch (beneficioCliente) {
            case 1:
                beneficioCli = new Comun();
                break;
            case 2:
                beneficioCli = new DeLaCasa();
                break;
            case 3:
                beneficioCli = new Preferencial();
                break;
        }

        cliente.setNombre(nombreCliente);

        cliente.setEmail(email);
        
        cliente.setBeneficioCliente(beneficioCli);
    }

    @Override
    public void leerComponente(ResultSet rs) throws SQLException {

    }

}
