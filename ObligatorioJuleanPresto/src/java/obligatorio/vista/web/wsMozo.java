package obligatorio.vista.web;

import com.google.gson.Gson;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import obligatorio.controladores.ControladorMozo;
import obligatorio.controladores.VistaMozo;
import obligatorio.modelo.Mesa;
import java.util.List;
import javax.websocket.server.PathParam;
import obligatorio.modelo.Mozo;
import obligatorio.modelo.Usuario;
import obligatorio.vista.web.dto.MesaDTO;
import obligatorio.vista.web.dto.MozoDTO;
import obligatorio.vista.web.dto.WsMessageDTO;
import obligatorio.vista.web.utils.MessageConverter;
import obligatorio.vista.web.utils.WsSessionHandler;
import obligatorio.vista.web.utils.WsUtils;

@ServerEndpoint(value = "/wsMozo/{userId}")
public class wsMozo implements VistaMozo {

    private ControladorMozo controlador;
    private Session session;
    private Gson gson;
    private Usuario mozo;
    private List<Mesa> listaMesas;

    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        // System.out.println(session.getId());
        this.session = session;
        this.gson = new Gson();
        Usuario usuario = WsSessionHandler.getItem("usuario");
        this.mozo = usuario;
        controlador = new ControladorMozo(this, usuario);       
    }

    @OnMessage
    public void onMessage(String message) {        
         MesaDTO mesaDto = gson.fromJson(message, MesaDTO.class);         
         this.controlador.CambiarEstadoMesa(mesaDto.getNumero(), true);
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void obtenerMozo(String userId) {
        MozoDTO mozoDto = adaptarMozo(this.mozo);
        WsMessageDTO msgTipos = new WsMessageDTO(WsMessageDTO.TipoMensaje.TIPO_MOSTRAR_MOZO, mozoDto);
        String mensaje = MessageConverter.toMessage(msgTipos);
        WsUtils.enviarMensajePorSocket(session, mensaje);
    }
    
    private MozoDTO adaptarMozo(Usuario mozo) {
        MozoDTO mozoDto = new MozoDTO(mozo.getNombreUsuario(), mozo.getPassword(), mozo.getNombreCompleto(), mozo.getUserId());
        List<MesaDTO> mesasDto = new ArrayList<>();
        mozoDto.setNombreCompleto(mozo.getNombreCompleto());        
        for (Mesa mesa : mozo.obtenerMesas()) {
            mesasDto.add(new MesaDTO(mesa.getNumero(), mesa.getEstado()));        }        
        mozoDto.setMesas(mesasDto);        
        return mozoDto;
    }

}
