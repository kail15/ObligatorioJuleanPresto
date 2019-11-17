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
public class Cliente {
    private int oid;
    private String nombre;
    private String email;

    public Cliente(int id, String nombre, String email) {
        this.oid = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Cliente() {
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
