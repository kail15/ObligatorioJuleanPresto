
package obligatorio.modelo;


public class DeLaCasa implements BeneficioCliente{

    public DeLaCasa() {
    } 
    

    @Override
    public double calcularDescuento(Servicio servicio) {
        return 500;
    }
    
}
