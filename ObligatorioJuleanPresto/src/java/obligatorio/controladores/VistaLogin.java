/*
ObJP
 */
package obligatorio.controladores;

import obligatorio.modelo.Usuario;


public interface VistaLogin {
    
    public void ingresarUsuario(Usuario usuario);    
    
    public void mostrarError(String error);
    
}
