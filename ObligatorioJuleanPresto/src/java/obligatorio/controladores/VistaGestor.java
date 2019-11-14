/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.controladores;

import java.util.List;
import obligatorio.modelo.Pedido;
import obligatorio.modelo.UnidadProcesadora;

/**
 *
 * @author Usuario
 */
public interface VistaGestor {
    
    public void cargarUnidad();
    
    public void obtenerPedidos(List<Pedido> pedidos);
    
    public void mostrarUnidades(List<UnidadProcesadora> unidades);

    public void obtenerPedidosTotales(List<Pedido> pedidos);
}
