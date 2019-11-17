
package obligatorio.modelo;

import java.util.List;

public class Mesa {
    private int numero;
    private boolean estado;
    private Servicio servicio = new Servicio();

    public Mesa(int numero) {
        this.numero = numero;
    }  
    
    public Mesa(int numero, boolean estado) {
        this.numero = numero;
        this.estado = estado;
    } 
    
    public Mesa() {        
    }    

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }   

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }   
    
    public void agregarPedidoAservicio(Pedido p){
      this.servicio.agregarPedido(p);
    }
    
    public List<Pedido> getPedidosServicio(){
      return this.servicio.getPedidos();
    }
}
