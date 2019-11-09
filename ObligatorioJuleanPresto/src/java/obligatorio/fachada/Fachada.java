package obligatorio.fachada;

import java.util.List;
import java.util.Observable;
import obligatorio.exceptions.CredencialesInvalidasException;
import obligatorio.exceptions.UsuarioInactivoException;
import obligatorio.exceptions.UsuarioLogueadoException;
import obligatorio.modelo.Gestor;
import obligatorio.modelo.Mesa;
import obligatorio.modelo.MesaTransferida;
import obligatorio.modelo.Mozo;
import obligatorio.modelo.Producto;
import obligatorio.modelo.SistemaMesasTransferidas;
import obligatorio.modelo.SistemaProductos;
import obligatorio.modelo.SistemaUnidadProcesadora;
import obligatorio.modelo.SistemaUsuarios;
import obligatorio.modelo.UnidadProcesadora;
import obligatorio.modelo.Usuario;

//OJP
public class Fachada extends Observable {

    private SistemaUsuarios sistemaUsuarios;
    private SistemaUnidadProcesadora sistemaUnidades;
    private SistemaProductos sistemaProductos;
    private SistemaMesasTransferidas sistemaMesasTransferidas;

    private static Fachada instancia;

    private Fachada() {
        sistemaUsuarios = new SistemaUsuarios();
        sistemaProductos = new SistemaProductos();
        sistemaUnidades = new SistemaUnidadProcesadora();
        cargar();
    }

    public static Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    public enum EVENTOS {
        RECIBIR_PEDIDO,
        TRANSFERIR_MESA;
    };

    public Usuario login(String n, String p) throws CredencialesInvalidasException, UsuarioInactivoException, UsuarioLogueadoException {

        Usuario usuario = sistemaUsuarios.login(n, p);
        if (usuario != null) {
            agregarUsuarioLogueado(usuario);
        }
        return usuario;
    }

    public List<Producto> obtenerProductos() {
        return this.sistemaProductos.getProductos();
    }

    public void agregarUsuario(Usuario u) {
        sistemaUsuarios.agregarUsuario(u);
    }

    public void agregarUsuarioLogueado(Usuario u) {
        sistemaUsuarios.agregarUsuarioLogueados(u);
    }

    public void agregarUnidad(UnidadProcesadora u) {
        this.sistemaUnidades.agregarUnidad(u);
    }

    public void agregarProducto(Producto p) {
        this.sistemaProductos.agregarProducto(p);
    }
    
    public List<Producto> actualizarProductos(Producto prod){
       return this.sistemaProductos.actualizarProductos(prod);
    }
    
    public void agregarMesaTransf(MesaTransferida mesa){
       this.sistemaMesasTransferidas.agregarMesa(mesa);
    }
    
    public void elimiarMesaTransf(MesaTransferida mesa){
      this.sistemaMesasTransferidas.elimiarMesa(mesa);
    }

    public void cargar() {

        Usuario carlos = new Mozo("4", "user1", "pass1", "carlos valdez");
        Usuario pedro = new Mozo("5", "user3", "pass3", "pedro mendez");
        Usuario juan = new Gestor("2", "user2", "pass2", "juan perez");
        Mesa mesa1 = new Mesa(1);
        Mesa mesa2 = new Mesa(2);
        Mesa mesa3 = new Mesa(3);
        mesa3.setEstado(true);
        mesa2.setEstado(true);
        Mesa mesa4 = new Mesa(4);

        Mesa mesa5 = new Mesa(5);
        Mesa mesa6 = new Mesa(6);

        //unidad procesadora
        UnidadProcesadora cocina = new UnidadProcesadora(1, "Cocina");
        UnidadProcesadora bar = new UnidadProcesadora(2, "bar");

        //productos
        Producto prod1 = new Producto(1, "hamburguesa", 90.0, 100, cocina);
        Producto prod3 = new Producto(3, "papas", 80.0, 100, cocina);
        Producto prod2 = new Producto(2, "cerveza", 120.0, 100, bar);

        carlos.agregarMesa(mesa1);
        carlos.agregarMesa(mesa2);
        carlos.agregarMesa(mesa3);
        carlos.agregarMesa(mesa4);

        carlos.agregarMesa(mesa5);
        carlos.agregarMesa(mesa6);

        this.agregarUsuario(carlos);
        this.agregarUsuario(pedro);
        this.agregarUsuario(juan);

        this.agregarProducto(prod1);
        this.agregarProducto(prod2);
        this.agregarProducto(prod3);

        this.agregarUnidad(bar);
        this.agregarUnidad(cocina);
    }
}
