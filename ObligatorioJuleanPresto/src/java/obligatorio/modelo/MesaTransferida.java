/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio.modelo;

import obligatorio.vista.web.utils.EventoMensaje;

/**
 *
 * @author Usuario
 */
public class MesaTransferida {

    private int numeroMesa;
    private String mozoOrigen;
    private String mozoDestino;
    private String mozoOrigenNombre;
    private String mozoDestinoNombre;
    private boolean aceptaMesa;
    private boolean estadoMesa;

    public MesaTransferida(int numero, String mozoOrigen,
            String mozoDestino, String mozoOrigenNombre, String mozoDestinoNombre, boolean estadoMesa) {
        this.numeroMesa = numero;
        this.mozoOrigen = mozoOrigen;
        this.mozoDestino = mozoDestino;
        this.mozoOrigenNombre = mozoOrigenNombre;
        this.mozoDestinoNombre = mozoDestinoNombre;
        this.estadoMesa = estadoMesa;
    }

    public int getNumero() {
        return numeroMesa;
    }

    public void setNumero(int numero) {
        this.numeroMesa = numero;
    }

    public String getMozoOrigen() {
        return mozoOrigen;
    }

    public void setMozoOrigen(String mozoOrigen) {
        this.mozoOrigen = mozoOrigen;
    }

    public String getMozoDestino() {
        return mozoDestino;
    }

    public void setMozoDestino(String mozoDestino) {
        this.mozoDestino = mozoDestino;
    }

    public String getMozoOrigenNombre() {
        return mozoOrigenNombre;
    }

    public void setMozoOrigenNombre(String mozoOrigenNombre) {
        this.mozoOrigenNombre = mozoOrigenNombre;
    }

    public String getMozoDestinoNombre() {
        return mozoDestinoNombre;
    }

    public void setMozoDestinoNombre(String mozoDestinoNombre) {
        this.mozoDestinoNombre = mozoDestinoNombre;
    }

    public boolean isAceptaMesa() {
        return aceptaMesa;
    }

    public void setAceptaMesa(boolean aceptaMesa) {
        this.aceptaMesa = aceptaMesa;
    }

    public boolean isEstadoMesa() {
        return estadoMesa;
    }

    public void setEstadoMesa(boolean estadoMesa) {
        this.estadoMesa = estadoMesa;
    }
   

}
