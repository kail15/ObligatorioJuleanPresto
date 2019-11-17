/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumnoFI
 */
public class BaseDatos {
    
    private static BaseDatos instancia = new BaseDatos();
    private Connection conexion;
    private Statement stmt;

    public static BaseDatos getInstancia() {
        return instancia;
    }
    private BaseDatos() {
    }
    
    public void conectarse(String driver,String url,String usr,String pass){
        try{
            //Ejemplo url jdbc:mysql://localhost:3306/DDAM4a"
            //Driver MySql "com.mysql.jdbc.Driver"
            
            Class.forName(driver); //"carga la clase del driver"
            conexion = DriverManager.getConnection(url, usr, pass); //se conecta
            stmt = conexion.createStatement();
            
            System.out.println("CONECTADO!");
        }catch(Exception e){
            System.out.println("Error al conectar:" + e.getMessage());
        }
    }
    public void desconectar(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            
        }
    }
    //Para insert, update, delete
    public int modificar(String sql){
        try {
            return stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Error al modificar:" + ex.getMessage());
            System.out.println("SQL:" + sql);
            return -1;
        }
    }
    //Para SELECT
    public ResultSet consultar(String sql){
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Error al consultar:" + ex.getMessage());
            System.out.println("SQL:" + sql);
            return null;
        }
    }
    
    public boolean transaccion(ArrayList<String> sqls){
        try{
            conexion.setAutoCommit(false); //BEGIN T
            for(String sql:sqls){
                int r = modificar(sql);
                if(r==-1){ //fallo
                    conexion.rollback();
                    return false;
                }
            }
            conexion.commit();
            return true;
        }catch(SQLException e){
            System.out.println("Error al ejecutar transaccion:"  + e.getMessage());
            return false;
        }finally{
            try {
                conexion.setAutoCommit(true); //END T
            } catch (SQLException ex) {         }
        }
    }
}
