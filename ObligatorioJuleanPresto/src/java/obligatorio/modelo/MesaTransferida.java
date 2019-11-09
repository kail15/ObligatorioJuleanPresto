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
public class MesaTransferida {
    
    private int numero;
    private Usuario mozoOrigen;
    private Usuario mozoDestino;

    public MesaTransferida(int numero, Usuario mozoOrigen, Usuario mozoDestino) {
        this.numero = numero;
        this.mozoOrigen = mozoOrigen;
        this.mozoDestino = mozoDestino;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Usuario getMozoOrigen() {
        return mozoOrigen;
    }

    public void setMozoOrigen(Usuario mozoOrigen) {
        this.mozoOrigen = mozoOrigen;
    }

    public Usuario getMozoDestino() {
        return mozoDestino;
    }

    public void setMozoDestino(Usuario mozoDestino) {
        this.mozoDestino = mozoDestino;
    } 
    
}
