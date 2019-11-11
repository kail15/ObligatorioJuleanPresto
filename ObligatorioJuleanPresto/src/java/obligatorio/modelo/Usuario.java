package obligatorio.modelo;

import java.util.List;

public abstract class Usuario {

    private String nombreUsuario;
    private String password;
    private String nombreCompleto;
    private String userId;

    public Usuario(String userId, String nombreUsuario, String password, String nombreCompleto) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.userId = userId;
    }
    
    public Usuario() {
        
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

    public boolean validarUsuario(String id) {
        return id.equals(userId);
    }

    public abstract void agregarMesa(Mesa mesa);

    public List<Mesa> obtenerMesas() {
        return null;
    }
    
    public List<Mesa> CambiarEstadoMesa(int id, boolean estado){
        return null;
    }
    
    public void setMesas(List<Mesa> mesas) {
        
    }
    
    public void elimiarMesa(Mesa mesa){
       
    }
}
