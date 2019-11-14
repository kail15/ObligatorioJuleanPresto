
package obligatorio.vista.web.utils;

public class NotificarHelper {
    EventoMensaje evento;
    Object objetoNotificar;  
    boolean estado;

    public NotificarHelper(EventoMensaje evento, Object objetoNotificar) {
        this.evento = evento;
        this.objetoNotificar = objetoNotificar;
    }

    public EventoMensaje getEvento() {
        return evento;
    }

    public void setEvento(EventoMensaje evento) {
        this.evento = evento;
    }

    public Object getObjetoNotificar() {
        return objetoNotificar;
    }

    public void setObjetoNotificar(Object objetoNotificar) {
        this.objetoNotificar = objetoNotificar;
    } 

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
}
