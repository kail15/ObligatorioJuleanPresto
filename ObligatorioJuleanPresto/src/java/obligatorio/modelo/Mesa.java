
package obligatorio.modelo;

public class Mesa {
    private int numero;
    private boolean estado;

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
    
    
    
    
}
