
package obligatorio.vista.web.dto;


public class MesaTransferidaDTO {
    
    private int numero;
    private String mozoOrigen;
    private String mozoDestino;
    private String mozoOrigenNombre;
    private String mozoDestinoNombre;
    private boolean aceptaMesa;
    private boolean estadoMesa;

    public MesaTransferidaDTO(int numero, String mozoOrigen, String mozoDestino, boolean estadoMesa) {
        this.numero = numero;
        this.mozoOrigen = mozoOrigen;
        this.mozoDestino = mozoDestino;
        this.estadoMesa = estadoMesa;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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
