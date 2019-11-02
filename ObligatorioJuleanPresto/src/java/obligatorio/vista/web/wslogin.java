package obligatorio.vista.web;

import com.google.gson.Gson;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import obligatorio.controladores.ControladorLogin;
import obligatorio.controladores.VistaLogin;
import obligatorio.modelo.Usuario;
import obligatorio.vista.web.dto.DatosLoginDTO;
import obligatorio.vista.web.dto.WsMessageDTO;

//OJP
@ServerEndpoint("/wslogin")
public class wslogin implements VistaLogin {

    private ControladorLogin controlador;
    private Session session;
    private Gson gson;
    
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.getId());
        this.session = session;
        this.gson = new Gson();
        controlador = new ControladorLogin(this);
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Mensaje recibido: " + message);

        DatosLoginDTO datos = gson.fromJson(message, DatosLoginDTO.class);
        this.controlador.login(datos.getUsername(), datos.getPassword());
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void ingresarUsuario(Usuario usuario) {
        WsMessageDTO msgLogin = new WsMessageDTO(WsMessageDTO.TipoMensaje.TIPO_IR_MENU_MOZO, usuario.getUserId());
        String mensaje = MessageConverter.toMessage(msgLogin); 
        WsSessionHandler.setItem("usuario", usuario); 
        WsUtils.enviarMensajePorSocket(session, mensaje);
    }

    @Override
    public void mostrarError(String error) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    

}
