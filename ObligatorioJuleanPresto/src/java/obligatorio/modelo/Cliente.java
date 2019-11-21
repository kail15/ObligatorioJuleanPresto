
package obligatorio.modelo;


public class Cliente {
    private int oid;
    private String nombre;
    private String email;
    private BeneficioCliente beneficioCliente;


    public Cliente(int oid, String nombre) {
        this.oid = oid;
        this.nombre = nombre;
    }

    public Cliente(int oid, String nombre, String email) {
        this.oid = oid;
        this.nombre = nombre;
        this.email = email;
    }
    
    public Cliente(){
    }

    public int getId() {
        return oid;
    }

    public void setId(int oid) {
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
 
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public BeneficioCliente getBeneficioCliente() {
        return beneficioCliente;
    }

    public void setBeneficioCliente(BeneficioCliente beneficioCliente) {
        this.beneficioCliente = beneficioCliente;
    } 
    
    public double getDescuento(Servicio servicio){
       return this.beneficioCliente.calcularDescuento(servicio);
    }    
}
