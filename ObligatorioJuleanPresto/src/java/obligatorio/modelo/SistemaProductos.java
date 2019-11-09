package obligatorio.modelo;

import java.util.ArrayList;

public class SistemaProductos {
    
    private ArrayList<Producto> productos;
    
    public SistemaProductos() {
        this.productos = new ArrayList();
    }
    
    public ArrayList<Producto> getProductos() {
        return productos;
    }
    
    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    
    public void agregarProducto(Producto p) {
        this.productos.add(p);
    }   
    
}
