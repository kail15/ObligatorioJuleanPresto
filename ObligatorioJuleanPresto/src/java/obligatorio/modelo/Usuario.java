
package obligatorio.modelo;


public abstract class Usuario {
    private String nombreUsuario;
    private String password;
    private String nombreCompleto;
    private String userId;


    public Usuario(String nombreUsuario, String password, String nombreCompleto) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getUserId() {
        return userId;
    }
    
    ////
    
    public boolean validarLogin(String n, String p) {
        return n.equals(nombreUsuario) && p.equals(password);
    }
    
    public abstract void agregarMesa(Mesa mesa);
    
    
}
