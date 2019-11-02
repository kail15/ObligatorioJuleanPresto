
package obligatorio.modelo;

public class Mesa {
    private int numero;
    private Mozo mozo;
    private EstadoMesa estado;

    public Mesa(int numero, Mozo mozo, EstadoMesa estado) {
        this.numero = numero;
        this.mozo = mozo;
        this.estado = estado;
    } 
    
    public Mesa(int numero) {
        this.numero = numero;      
    }  

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Mozo getMozo() {
        return mozo;
    }

    public void setMozo(Mozo mozo) {
        this.mozo = mozo;
    }

    public EstadoMesa getEstado() {
        return estado;
    }

    public void setEstado(EstadoMesa estado) {
        this.estado = estado;
    }
    
    
    
    
}
