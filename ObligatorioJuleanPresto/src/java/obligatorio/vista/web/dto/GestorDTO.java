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
public class GestorDTO {
    
    private String nombreCompleto;
    private String userId;
    private UnidadProcesadoraDTO unidad;

    public GestorDTO(String nombreCompleto, String userId, UnidadProcesadoraDTO unidad) {
        this.nombreCompleto = nombreCompleto;
        this.userId = userId;
        this.unidad = unidad;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UnidadProcesadoraDTO getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadProcesadoraDTO unidad) {
        this.unidad = unidad;
    }
    
    
    
}
