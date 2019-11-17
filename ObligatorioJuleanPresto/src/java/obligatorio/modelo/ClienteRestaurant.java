
package obligatorio.modelo;


public class ClienteRestaurant {
    
    private BeneficioCliente beneficio;

    public ClienteRestaurant(BeneficioCliente beneficio) {
        this.beneficio = beneficio;
    }

    public ClienteRestaurant() {
    }
    
    public double getDescuento(Servicio servicio){
       return this.beneficio.calcularDescuento(servicio);
    }
    

    public BeneficioCliente getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(BeneficioCliente beneficio) {
        this.beneficio = beneficio;
    }    
}
