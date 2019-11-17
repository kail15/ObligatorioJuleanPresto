
package obligatorio.modelo;


public class Cliente {
    private int id;
    private String nombre;
    private String email;
    private int beneficio;

    public Cliente(int id, String nombre, int beneficio) {
        this.id = id;
        this.nombre = nombre;
        this.beneficio = beneficio;
    }
    
    public Cliente(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
