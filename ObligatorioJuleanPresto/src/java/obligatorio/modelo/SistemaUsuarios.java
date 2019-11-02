/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.modelo;

import java.util.ArrayList;
import obligatorio.exceptions.CredencialesInvalidasException;
import obligatorio.exceptions.UsuarioInactivoException;

/**
 *
 * @author Usuario
 */
public class SistemaUsuarios {
        private ArrayList<Usuario> usuarios;
        
        public SistemaUsuarios(){
        usuarios = new ArrayList();    
    }
        
        public Usuario login(String n,String p) throws CredencialesInvalidasException, UsuarioInactivoException{
        Usuario usuarioLogueado = null;
        for(Usuario user:usuarios ){
            /*if(user.validarLogin(n,p)){
                  usuarioLogueado = user;
            }*/
        }
        if(usuarioLogueado == null){
            throw new CredencialesInvalidasException("Credenciales invalidas");
        }else{
            // Esto es para forzar una exception 
           /* if(usuarioLogueado.getUsername().equals("a")){
                throw new UsuarioInactivoException("El usuario se encuentra inactivo, contacte al admin");
            }*/
            return usuarioLogueado;
        }
        
    }

}
