
package obligatorio.modelo;

public class Preferencial implements BeneficioCliente{

    public Preferencial() {
    } 
    

    @Override
    public double calcularDescuento(Servicio servicio) {
        
        double precioTotal = servicio.getPrecioTotal();
        double precioAux = precioTotal;
        double descuento = 0;

        // 6 es el id del agua
        for (Pedido p : servicio.getPedidos()) {
            if (p.getProducto().getCodigo() == 6) {
                precioAux -= p.getProducto().getPrecioUnitario();
            }
        }
        
        if(precioTotal > 2000){
            precioTotal = precioTotal * 0.095;
        }
        
        descuento = precioTotal - precioAux;        
        
        return descuento;
    }
    
    
}
