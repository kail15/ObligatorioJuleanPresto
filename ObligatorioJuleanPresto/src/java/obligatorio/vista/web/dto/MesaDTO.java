
package obligatorio.vista.web.dto;


public class MesaDTO {
    
    private int numero;
    private boolean estado;
    private ServicioDTO servicio = new ServicioDTO();

    public MesaDTO(int numero, boolean estado) {
        this.numero = numero;
        this.estado = estado;
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

    public ServicioDTO getServicio() {
        return servicio;
    }

    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
    }   
    
    public void agregarPedidoAservicio(PedidoDTO p){
      this.servicio.agregarPedido(p);
    }
    
    public double calcularPrecioTotal() {
       return this.servicio.calcularPrecioTotal();    
    }
    
    public void setPrecioServicio(double total){
      this.servicio.setPrecioServicio(total);
    }
}
