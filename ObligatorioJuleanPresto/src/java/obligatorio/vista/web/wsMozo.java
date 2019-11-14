package obligatorio.vista.web;

import com.google.gson.Gson;
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
import obligatorio.modelo.MesaTransferida;
import obligatorio.modelo.Mozo;
import obligatorio.modelo.Pedido;
import obligatorio.modelo.Producto;
import obligatorio.modelo.UnidadProcesadora;
import obligatorio.modelo.Usuario;
import obligatorio.vista.web.dto.MesaDTO;
import obligatorio.vista.web.dto.MesaTransferidaDTO;
import obligatorio.vista.web.dto.MozoDTO;
import obligatorio.vista.web.dto.PedidoDTO;
import obligatorio.vista.web.dto.ProductoDTO;
import obligatorio.vista.web.dto.UnidadProcesadoraDTO;
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
    private List<Producto> productos;

    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        this.session = session;
        this.gson = new Gson();
        String userSession = "usuario" + userId;
        Usuario usuario = WsSessionHandler.getItem(userSession);
        this.mozo = usuario;
        controlador = new ControladorMozo(this, usuario);
    }

    @OnMessage
    public void onMessage(String message) {

        WsMessageDTO messageDto = gson.fromJson(message, WsMessageDTO.class);
        if (null != messageDto.getTipo()) {
            switch (messageDto.getTipo()) {
                case TIPO_CAMBIAR_ESTADO_MESA:
                    MesaDTO mesaDto = gson.fromJson(message, MesaDTO.class);
                    CambiarEstadoMesa(mesaDto.getNumero(), mesaDto.getEstado());
                    break;
                case TIPO_AGREGAR_PEDIDO:
                    PedidoDTO pedidoDto = gson.fromJson(message, PedidoDTO.class);
                    enviarPedido(pedidoDto);
                    break;
                case TIPO_TRANSFERIR_MESA:
                    MesaTransferidaDTO mesaDtotransf = gson.fromJson(message, MesaTransferidaDTO.class);
                    enviarMesaTransf(mesaDtotransf);
                    break;
                case TIPO_ACEPTAR_MESA:
                    MesaTransferidaDTO mesaDtoAcept = gson.fromJson(message, MesaTransferidaDTO.class);
                    confirmarMesaTransf(mesaDtoAcept);
                    break;
                case TIPO_LOGOUT:
                    MozoDTO mozoDto = gson.fromJson(message, MozoDTO.class);
                    logout(mozoDto);
                    break;
                default:
                    break;
            }
        }
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

    @Override
    public void CambiarEstadoMesa(int mesaNumero, boolean estado) {
        List<Mesa> mesasMozo;
        mesasMozo = this.controlador.CambiarEstadoMesa(mesaNumero, estado);
        this.mozo.setMesas(mesasMozo);
        MozoDTO mozoDto = adaptarMozo(this.mozo);
        WsMessageDTO msg = new WsMessageDTO(WsMessageDTO.TipoMensaje.TIPO_CAMBIAR_ESTADO_MESA, mozoDto);
        String mensaje = MessageConverter.toMessage(msg);
        WsUtils.enviarMensajePorSocket(session, mensaje);
    }

    @Override
    public void transferirMesa(MesaTransferida mesa) {

        MesaTransferidaDTO mesaDto = adatparMesaTrasf(mesa);

        WsMessageDTO msg = new WsMessageDTO(WsMessageDTO.TipoMensaje.TIPO_TRANSFERIR_MESA_ENVIAR, mesaDto);
        String mensaje = MessageConverter.toMessage(msg);
        WsUtils.enviarMensajePorSocket(session, mensaje);
    }

    @Override
    public void aceptarMesaTransf(MesaTransferida mesa) {

        obtenerMozo(this.mozo.getUserId());

        MesaTransferidaDTO mesaDto = adatparMesaTrasf(mesa);

        WsMessageDTO msg = new WsMessageDTO(WsMessageDTO.TipoMensaje.TIPO_ACEPTAR_MESA, mesaDto);
        String mensaje = MessageConverter.toMessage(msg);
        WsUtils.enviarMensajePorSocket(session, mensaje);
    }

    private void enviarMesaTransf(MesaTransferidaDTO mesa) {
        MesaTransferida mesaTransf = new MesaTransferida(mesa.getNumero(), mesa.getMozoOrigen(),
                mesa.getMozoDestino(), mesa.getMozoOrigenNombre(), mesa.getMozoDestinoNombre(), mesa.isEstadoMesa());
        this.controlador.transferirMesa(mesaTransf);
    }

    private void enviarPedido(PedidoDTO pedidoDto) {

        //  this.controlador.agregarPedido(prod);
    }

    private void confirmarMesaTransf(MesaTransferidaDTO mesa) {
        MesaTransferida mesaTransf = new MesaTransferida(mesa.getNumero(), mesa.getMozoOrigen(),
                mesa.getMozoDestino(), mesa.getMozoOrigenNombre(), mesa.getMozoDestinoNombre(), mesa.isEstadoMesa());
        mesaTransf.setAceptaMesa(mesa.isAceptaMesa());

        this.controlador.aceptarMesaTransf(mesaTransf);
    }

    @Override
    public void obtenerProductos(List<Producto> productos) {
        this.productos = productos;
        List<ProductoDTO> productosAdaptadosDto = this.adaptarProductos(productos);
        WsMessageDTO msg = new WsMessageDTO(WsMessageDTO.TipoMensaje.TIPO_OBTENER_PRODUCTOS, productosAdaptadosDto);
        String mensaje = MessageConverter.toMessage(msg);
        WsUtils.enviarMensajePorSocket(session, mensaje);
    }

    @Override
    public void obtenerMozosLogueados(List<Usuario> mozos) {

        List<MozoDTO> mozosLog = new ArrayList<>();
        mozos.forEach((m) -> {
            if (m instanceof Mozo) {
                mozosLog.add(adaptarMozo(m));
            }
        });

        WsMessageDTO msgTipos = new WsMessageDTO(WsMessageDTO.TipoMensaje.TIPO_MOZOS_LOGUEADOS, mozosLog);
        String mensaje = MessageConverter.toMessage(msgTipos);
        WsUtils.enviarMensajePorSocket(session, mensaje);
    }
    
    private void logout(MozoDTO mozoDto){
       Mozo mozoLogout = new Mozo();
       mozoLogout.setUserId(mozoDto.getUserId());
       this.controlador.logout(mozoLogout);
    }

    private MozoDTO adaptarMozo(Usuario mozo) {
        MozoDTO mozoDto = new MozoDTO(mozo.getNombreUsuario(), mozo.getPassword(), mozo.getNombreCompleto(), mozo.getUserId());
        List<MesaDTO> mesasDto = new ArrayList<>();
        mozoDto.setNombreCompleto(mozo.getNombreCompleto());
        mozo.obtenerMesas().forEach((mesa) -> {
            mesasDto.add(new MesaDTO(mesa.getNumero(), mesa.getEstado()));
        });
        mozoDto.setMesas(mesasDto);
        return mozoDto;
    }

    private List<ProductoDTO> adaptarProductos(List<Producto> productos) {
        List<ProductoDTO> productosDTO = new ArrayList<>();

        productos.forEach((p) -> {
            productosDTO.add(new ProductoDTO(p.getCodigo(), p.getNombre(),
                    p.getPrecioUnitario(), p.getStockDisponible(), adaptarUP(p.getUnidadProcesadora())));
        });
        return productosDTO;
    }

    private UnidadProcesadoraDTO adaptarUP(UnidadProcesadora u) {
        return new UnidadProcesadoraDTO(u.getId(), u.getNombre());
    }

    private MesaTransferidaDTO adatparMesaTrasf(MesaTransferida mesa) {
        MesaTransferidaDTO mesaDto = new MesaTransferidaDTO(mesa.getNumero(), mesa.getMozoOrigen(), mesa.getMozoDestino(), mesa.isEstadoMesa());
        mesaDto.setMozoOrigenNombre(mesa.getMozoOrigenNombre());
        mesaDto.setMozoDestinoNombre(mesa.getMozoDestinoNombre());
        mesaDto.setAceptaMesa(mesa.isAceptaMesa());
        return mesaDto;
    }

    private Pedido adaptarPedido(PedidoDTO pedidoDto) {
        // Producto producto, int cantidad, String descripcion, Mesa mesa, Mozo mozo, EstadoPedido estado
        // int codigo, String nombre, double precioUnitario, int stockDisponible, UnidadProcesadora unidadProcesadora
        /*Producto prod =  new Producto
        return new Pedido();*/
        return null;
    }

    @Override
    public void mostrarError(String error) {
        
        WsMessageDTO msgTipos = new WsMessageDTO(WsMessageDTO.TipoMensaje.ERROR_LOGOUT, error);
        String mensaje = MessageConverter.toMessage(msgTipos);
        WsUtils.enviarMensajePorSocket(session, mensaje); 
    }

    @Override
    public void logoutMozo(Usuario usuario) {
        MozoDTO mozoDto = new MozoDTO();
        mozoDto.setUserId(usuario.getUserId());
        WsMessageDTO msgTipos = new WsMessageDTO(WsMessageDTO.TipoMensaje.TIPO_LOGOUT, mozoDto);
        String mensaje = MessageConverter.toMessage(msgTipos);
        WsUtils.enviarMensajePorSocket(session, mensaje); 
        
    }

}
