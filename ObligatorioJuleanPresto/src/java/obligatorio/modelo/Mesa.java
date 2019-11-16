
package obligatorio.modelo;
import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private int numero;
    private boolean estado;
    private List<Servicio> mesas = new ArrayList<>();

    public Mesa(int numero) {
        this.numero = numero;
    }  
    
    public Mesa(int numero, boolean estado) {
        this.numero = numero;
        this.estado = estado;
    } 
    
    public Mesa() {        
    }    

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }   

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<Servicio> getMesas() {
        return mesas;
    }

    public void setMesas(List<Servicio> mesas) {
        this.mesas = mesas;
    }       
}
