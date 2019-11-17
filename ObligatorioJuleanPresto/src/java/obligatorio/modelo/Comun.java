package obligatorio.modelo;

public class Comun implements BeneficioCliente {

    public Comun() {
    }   
    

    @Override
    public double calcularDescuento(Servicio servicio) {
        double precioTotal = servicio.getPrecioTotal();
        double precioAux = precioTotal;
        double descuento = 0;

        // 5 es el id del cafe
        for (Pedido p : servicio.getPedidos()) {
            if (p.getProducto().getCodigo() == 5) {
                precioAux -= p.getProducto().getPrecioUnitario();
            }
        }
        
        descuento = precioTotal - precioAux;
        return descuento;
    }

}
