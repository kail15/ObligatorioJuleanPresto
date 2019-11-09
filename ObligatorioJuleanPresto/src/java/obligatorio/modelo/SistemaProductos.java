package obligatorio.modelo;

import java.util.ArrayList;
import java.util.List;

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
    
    public List<Producto> actualizarProductos(Producto prod){       
        this.productos.forEach((p) ->{
            if(p.getCodigo() == prod.getCodigo()){
                int stock = p.getStockDisponible() - prod.getStockDisponible();
               p.setStockDisponible(stock);
            }        
        });        
        return this.productos;    
    };
    
}
