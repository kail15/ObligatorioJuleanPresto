
package obligatorio.controladores;

import java.util.List;
import obligatorio.modelo.Producto;
import obligatorio.modelo.Usuario;


public interface VistaMozo {
    
    public void obtenerMozo(String userId);
    
    public void CambiarEstadoMesa(int mesaNumero , boolean estado);
    
    public void obtenerProductos(List<Producto> productos);
    
}
