/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.modelo;

/**
 *
 * @author Usuario
 */
public class Transferencia {
    private Mesa mesa;
    private Mozo mozoOrigen;
    private Mozo mozoDestino;
    private EstadoTransferencia estado;

    public Transferencia(Mesa mesa, Mozo mozoOrigen, Mozo mozoDestino, EstadoTransferencia estado) {
        this.mesa = mesa;
        this.mozoOrigen = mozoOrigen;
        this.mozoDestino = mozoDestino;
        this.estado = estado;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Mozo getMozoOrigen() {
        return mozoOrigen;
    }

    public void setMozoOrigen(Mozo mozoOrigen) {
        this.mozoOrigen = mozoOrigen;
    }

    public Mozo getMozoDestino() {
        return mozoDestino;
    }

    public void setMozoDestino(Mozo mozoDestino) {
        this.mozoDestino = mozoDestino;
    }

    public EstadoTransferencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoTransferencia estado) {
        this.estado = estado;
    }
    
    
}
