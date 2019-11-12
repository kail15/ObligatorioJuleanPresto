/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.controladores;

import java.util.List;
import obligatorio.modelo.Pedido;

/**
 *
 * @author Usuario
 */
public interface VistaGestor {
    
    public void cargarUnidad();
    
    public void obtenerPedidos(List<Pedido> pedidos);
}
