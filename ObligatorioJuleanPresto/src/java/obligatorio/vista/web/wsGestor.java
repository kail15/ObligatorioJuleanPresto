/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.vista.web;

import com.google.gson.Gson;
import java.util.List;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import obligatorio.controladores.ControladorGestor;
import obligatorio.controladores.VistaGestor;
import obligatorio.modelo.Pedido;
import obligatorio.modelo.UnidadProcesadora;
import obligatorio.modelo.Usuario;
import obligatorio.vista.web.dto.GestorDTO;
import obligatorio.vista.web.dto.UnidadProcesadoraDTO;
import obligatorio.vista.web.dto.WsMessageDTO;
import obligatorio.vista.web.utils.MessageConverter;
import obligatorio.vista.web.utils.WsSessionHandler;
import obligatorio.vista.web.utils.WsUtils;

@ServerEndpoint(value = "/wsGestor/{userId}")
public class wsGestor implements VistaGestor {

    private ControladorGestor controlador;
    private Session session;
    private Gson gson;
    private Usuario gestor;

    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        this.session = session;
        this.gson = new Gson();
        String userSession = "usuario" + userId;
        Usuario usuario = WsSessionHandler.getItem(userSession);
        this.gestor = usuario;
        controlador = new ControladorGestor(this, usuario);
    }

    @OnMessage
    public void onMessage(String message) {

        WsMessageDTO messageDto = gson.fromJson(message, WsMessageDTO.class);
        if (null != messageDto.getTipo()) {
            switch (messageDto.getTipo()) {
                case TIPO_CONFIRMAR_UNIDAD:
                    UnidadProcesadoraDTO unidadDto = gson.fromJson(message, UnidadProcesadoraDTO.class);
                    confirmarUnidad(unidadDto);

                    break;

                default:
                    break;
            }
        }
    }
    
    @Override
    public void obtenerPedidos(List<Pedido> pedidos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void cargarUnidad() {
        UnidadProcesadora unidad = this.gestor.getUnidadProcesadora();
        UnidadProcesadoraDTO unidadDTO = new UnidadProcesadoraDTO( unidad.getId() , unidad.getNombre());
        GestorDTO gestorDto = new GestorDTO(this.gestor.getNombreCompleto() , this.gestor.getUserId(),unidadDTO );
        
         WsMessageDTO msgTipos = new WsMessageDTO(WsMessageDTO.TipoMensaje.TIPO_MOSTRAR_GESTOR, gestorDto);
        String mensaje = MessageConverter.toMessage(msgTipos);
        WsUtils.enviarMensajePorSocket(session, mensaje);
        
    }

    private void confirmarUnidad(UnidadProcesadoraDTO unidadDto) {
       this.controlador.confirmarUnidad(adatparUP(unidadDto), this.gestor.getUserId());
    }
    
    

    private UnidadProcesadora adatparUP(UnidadProcesadoraDTO unidadDto) {
        UnidadProcesadora unidad = new UnidadProcesadora(unidadDto.getId(), unidadDto.getNombre());
        return unidad;
    }

    

    

}
