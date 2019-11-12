/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.vista.web.dto;

/**
 *
 * @author Usuario
 */
public class UnidadProcesadoraDTO {
    private int id;
    private String nombre;
    private String userId;
    

    public UnidadProcesadoraDTO(int id ,String userId, String nombre) {
        this.userId = userId;
        this.id = id;
        this.nombre = nombre;
    }
    
    public UnidadProcesadoraDTO(int id , String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    
    
}
