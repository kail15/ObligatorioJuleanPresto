/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.vista.web.utils;

import java.io.IOException;
import javax.websocket.Session;

/**
 *
 * @author Usuario
 */
public class WsUtils {
    
    public static void enviarMensajePorSocket(Session session, String mensaje) {
        System.out.println(mensaje);
        try {
            session.getBasicRemote().sendText(mensaje);
        } catch (IOException ex) {
             System.out.println(ex);
        }
    }
    
}
