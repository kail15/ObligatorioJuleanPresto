
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
                precioAux -= p.getProducto().getPrecioUnitario() * p.getCantidad();
            }
        }
        
        if(precioAux > 2000){
            precioAux = precioAux * 0.95;
        }
        
        descuento = precioTotal - precioAux;        
        
        return descuento;
    }
    
    
}
