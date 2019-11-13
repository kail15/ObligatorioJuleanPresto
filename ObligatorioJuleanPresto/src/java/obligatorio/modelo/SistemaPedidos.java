/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.modelo;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class SistemaPedidos {
    
    private ArrayList<Pedido> pedidos;

    public SistemaPedidos() {
        this.pedidos = new ArrayList();
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    } 
    
    
    public ArrayList<Pedido> getPedidosEnEspera(UnidadProcesadora unidad) {
        
        ArrayList<Pedido> pedidosEnEspera = new ArrayList();
        
         pedidos.forEach((p) ->{
             Producto prod =  p.getProducto();
             
             
             
           if(p.getProducto().validarUnidadProcesadora(unidad) && p.getEstado().equals(EstadoPedido.EN_ESPERA)){
              pedidosEnEspera.add(p);
           }
         });       
         
         return pedidosEnEspera;
    }

    public void agregarPedido(Pedido pedido) {
        if(pedido != null){
            this.pedidos.add(pedido);            
        }
    }
    
}
