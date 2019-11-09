
package obligatorio.vista.web.dto;


public class WsMessageDTO {
    
    private TipoMensaje tipo;
    private Object valor;
    
    public WsMessageDTO(TipoMensaje tipo, Object valor){
        this.tipo = tipo;
        this.valor = valor;
    }
    
    public static enum TipoMensaje {
        TIPO_IR_MENU_MOZO,
        TIPO_IR_MENU_GESTOR,
        TIPO_ERROR,
        TIPO_MOSTRAR_MOZO,
        TIPO_CAMBIAR_ESTADO_MESA,
        TIPO_OBTENER_PRODUCTOS,
        TIPO_AGREGAR_PEDIDO,
        TIPO_TRANSFERIR_MESA,
        TIPO_ACEPTAR_MESA,
        TIPO_PEDIDO_LISTO
        ;
    }; 
    
    public TipoMensaje getTipo() {
        return tipo;
    }

    public void setTipo(TipoMensaje tipo) {
        this.tipo = tipo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object mensaje) {
        this.valor = mensaje;
    }
    
}
