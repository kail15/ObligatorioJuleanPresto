
package obligatorio.vista.web.utils;

import java.util.HashMap;
import obligatorio.modelo.Usuario;

//OJP
public class WsSessionHandler {
    
    private static HashMap<String, Usuario> usuarios = new HashMap<>();
    
    public static void setItem(String clave, Usuario valor){
        usuarios.put(clave, valor);
    }
    
    public static Usuario getItem(String clave){
        return usuarios.get(clave);
    }
    
}
