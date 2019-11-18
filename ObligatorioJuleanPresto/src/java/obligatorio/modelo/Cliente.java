
package obligatorio.modelo;


public class Cliente {
    private int oid;
    private String nombre;
    private String email;
    private int beneficio;

    public Cliente(int oid, String nombre, int beneficio) {
        this.oid = oid;
        this.nombre = nombre;
        this.beneficio = beneficio;
    }

    public Cliente(int oid, String nombre, String email, int beneficio) {
        this.oid = oid;
        this.nombre = nombre;
        this.email = email;
        this.beneficio = beneficio;
    }
    
    public Cliente(){
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(int beneficio) {
        this.beneficio = beneficio;
    }  
    
}
