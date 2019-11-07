/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.vista.web.dto;

/**
 *
 * @author Usuario
 */
public class WsMessageDTO {
    
    private TipoMensaje tipo;
    private Object valor;

    
    public WsMessageDTO(TipoMensaje tipo, Object valor){
        this.tipo = tipo;
        this.valor = valor;
    }
    
    public static enum TipoMensaje {
        TIPO_IR_MENU_MOZO, TIPO_IR_MENU_GESTOR, TIPO_ERROR, TIPO_MOSTRAR_MOZO ;
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
