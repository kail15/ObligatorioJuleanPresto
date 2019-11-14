package obligatorio.controladores;

import java.util.List;
import obligatorio.modelo.Mesa;
import obligatorio.modelo.MesaTransferida;
import obligatorio.modelo.Producto;
import obligatorio.modelo.Usuario;

public interface VistaMozo {

    public void obtenerMozo(String userId);

    public void CambiarEstadoMesa(int mesaNumero, boolean estado);

    public void obtenerProductos(List<Producto> productos);

    // public void agregarPedido(Producto prod);
    // public void transferirMesa(List<MesaTransferida> mesa);
    
    public void transferirMesa(MesaTransferida mesa);

    public void aceptarMesaTransf(MesaTransferida mesa);

    public void obtenerMozosLogueados(List<Usuario> usuarios);
    
    public void mostrarError(String error);
    
    public void logoutMozo(Usuario usuario);


    // public void cargarUsuariosLogueados();
}
