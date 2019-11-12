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
public class Gestor extends Usuario {

    private UnidadProcesadora unidadProcesadora;

    public Gestor(String userId, String nombreUsuario, String password, String nombreCompleto) {
        super(userId, nombreUsuario, password, nombreCompleto);
    }

    @Override
    public UnidadProcesadora getUnidadProcesadora() {
        return unidadProcesadora;
    }

    @Override
    public void setUnidadProcesadora(UnidadProcesadora unidadProcesadora) {
        this.unidadProcesadora = unidadProcesadora;
    }

    @Override
    public void agregarMesa(Mesa mesa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
