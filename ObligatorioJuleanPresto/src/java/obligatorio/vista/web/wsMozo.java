package obligatorio.vista.web;

import com.google.gson.Gson;
import java.lang.reflect.Array;
import java.util.AbstractList;
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
import obligatorio.modelo.Producto;
import obligatorio.modelo.UnidadProcesadora;
import obligatorio.modelo.Usuario;
import obligatorio.vista.web.dto.MesaDTO;
import obligatorio.vista.web.dto.MozoDTO;
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
        Usuario usuario = WsSessionHandler.getItem("usuario");
        this.mozo = usuario;
        controlador = new ControladorMozo(this, usuario);
    }

    @OnMessage
    public void onMessage(String message) {

        WsMessageDTO messageDto = gson.fromJson(message, WsMessageDTO.class);
        if (null != messageDto.getTipo()) switch (messageDto.getTipo()) {
            case TIPO_CAMBIAR_ESTADO_MESA:
                MesaDTO mesaDto = gson.fromJson(message, MesaDTO.class);
                CambiarEstadoMesa(mesaDto.getNumero(), mesaDto.getEstado());
                break;
            case TIPO_AGREGAR_PEDIDO:
                ProductoDTO productoDto = gson.fromJson(message, ProductoDTO.class);
                break;
            case TIPO_TRANSFERIR_MESA:
                MesaDTO mesaDtotransf = gson.fromJson(message, MesaDTO.class);
                obtenerMesaTransferida(mesaDtotransf.getNumero());
                break;
            default:
                break;
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
        WsMessageDTO msgTipos = new WsMessageDTO(WsMessageDTO.TipoMensaje.TIPO_CAMBIAR_ESTADO_MESA, mozoDto);
        String mensaje = MessageConverter.toMessage(msgTipos);
        WsUtils.enviarMensajePorSocket(session, mensaje);
    }
    
    @Override
    public Mesa transferirMesa(int mesaNumero) {
        // List<Mesa> mesasMozo;
        return null;
        
    }   
    
    
    private Mesa obtenerMesaTransferida(int mesaNumero) {
        Mesa mesaTransf =  new Mesa();
        mesaTransf.setNumero(mesaNumero);
        return mesaTransf;
    }

    @Override
    public void obtenerProductos(List<Producto> productos) {
        this.productos = productos;
        List<ProductoDTO> productosAdaptadosDto = this.adaptarProductos(productos);
        WsMessageDTO msgTipos = new WsMessageDTO(WsMessageDTO.TipoMensaje.TIPO_OBTENER_PRODUCTOS, productosAdaptadosDto);
        String mensaje = MessageConverter.toMessage(msgTipos);
        WsUtils.enviarMensajePorSocket(session, mensaje);
    }
    
    @Override
    public void agregarPedido(Producto prod) {
       List<Producto> productos;
       productos = this.controlador.agregarPedido(prod);       
       this.productos = productos;        
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

}
