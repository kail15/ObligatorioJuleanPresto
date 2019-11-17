package obligatorio.fachada;

import java.util.List;
import observer.Observable;
import obligatorio.exceptions.CredencialesInvalidasException;
import obligatorio.exceptions.MesaException;
import obligatorio.exceptions.PedidoException;
import obligatorio.exceptions.UsuarioInactivoException;
import obligatorio.exceptions.UsuarioLogueadoException;
import obligatorio.modelo.BeneficioCliente;
import obligatorio.modelo.Cliente;
import obligatorio.modelo.ClienteRestaurant;
import obligatorio.modelo.Comun;
import obligatorio.modelo.DeLaCasa;
import obligatorio.vista.web.utils.EstadoPedido;
import obligatorio.vista.web.utils.EventoMensaje;
import obligatorio.modelo.Gestor;
import obligatorio.modelo.Mesa;
import obligatorio.modelo.MesaTransferida;
import obligatorio.modelo.Mozo;
import obligatorio.modelo.Pedido;
import obligatorio.modelo.Preferencial;
import obligatorio.modelo.Producto;
import obligatorio.modelo.Servicio;
import obligatorio.modelo.SistemaClientes;
import obligatorio.modelo.SistemaPedidos;
import obligatorio.modelo.SistemaProductos;
import obligatorio.modelo.SistemaUnidadProcesadora;
import obligatorio.modelo.SistemaUsuarios;
import obligatorio.modelo.UnidadProcesadora;
import obligatorio.modelo.Usuario;
import obligatorio.vista.web.dto.PedidoDTO;
import obligatorio.vista.web.dto.ServicioDTO;
import obligatorio.vista.web.dto.UnidadProcesadoraDTO;
import obligatorio.vista.web.utils.NotificarHelper;

//OJP
public class Fachada extends Observable {

    private SistemaUsuarios sistemaUsuarios;
    private SistemaUnidadProcesadora sistemaUnidades;
    private SistemaProductos sistemaProductos;
    private SistemaPedidos sistemaPedidos;
    private SistemaClientes sistemaClientes;

    private static Fachada instancia;

    private Fachada() {
        sistemaUsuarios = new SistemaUsuarios();
        sistemaProductos = new SistemaProductos();
        sistemaUnidades = new SistemaUnidadProcesadora();
        sistemaPedidos = new SistemaPedidos();
        sistemaClientes = new SistemaClientes();
        cargar();
    }

    public static Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    public void confirmarUnidad(UnidadProcesadora unidad, String userId) {
        this.sistemaUsuarios.confirmarUnidad(unidad, userId);
    }

    ///envia el pedido desde el mozo al gestor
    public void agregarPedido(Pedido pedido) throws PedidoException {
        Producto prod = productoById(pedido.getProducto().getCodigo());
        pedido.setProducto(prod);
        this.sistemaPedidos.agregarPedido(pedido);
        prod.setStockDisponible(prod.getStockDisponible() - pedido.getCantidad());
        Usuario mozo = usuarioById(pedido.getMozo().getUserId());
        Mesa mesa = mozo.obtenerMesaByNumero(pedido.getMesa().getNumero());
        mesa.agregarPedidoAservicio(pedido);
        NotificarHelper noti = new NotificarHelper(EventoMensaje.ENVIAR_PEDIDO, prod.getUnidadProcesadora());
        Fachada.getInstancia().notificar(noti);
        NotificarHelper notiMozo = new NotificarHelper(EventoMensaje.OBTENER_PEDIDOS, null);
        Fachada.getInstancia().notificar(notiMozo);
    }

    public void devolverPedidosEnEspera(UnidadProcesadoraDTO unidadPedido) {
        NotificarHelper noti = new NotificarHelper(EventoMensaje.OBTENER_PEDIDOS, unidadPedido);
        notificar(noti);
    }

    public List<Pedido> obtenerPedidosEnEspera(UnidadProcesadora unidad) {
        return this.sistemaPedidos.getPedidosEnEspera(unidad);
    }

    public List<Pedido> obtenerPedidosByUnidad(UnidadProcesadora unidad) {
        return this.sistemaPedidos.getPedidosByUnidad(unidad);
    }

    private void agregarPedidoSistema(Pedido pedido) {
        this.sistemaPedidos.cargarPedido(pedido);
    }

    public void logout(Mozo mozo) throws MesaException {
        boolean ret = this.sistemaUsuarios.logout(mozo);
        NotificarHelper noti = new NotificarHelper(EventoMensaje.LOGOUT, mozo);
        noti.setEstado(ret);
        notificar(noti);
    }

    public void cambiarEstadoPedido(PedidoDTO p) {
        Pedido unPedido = new Pedido();
        unPedido.setPedidoId(p.getPedidoId());
        unPedido.setEstado(p.getEstado());
        Usuario gestor = usuarioById(p.getGestorId());
        unPedido.setGestor(gestor);

        this.sistemaPedidos.cambiarEstadoPedido(unPedido);
        Producto prod = new Producto();
        prod.setCodigo(p.getPedidoId());
        UnidadProcesadora unidad = unidadProcByProd(prod);
        NotificarHelper helper = new NotificarHelper(EventoMensaje.PEDIDO_PROCESADO, unidad);
        notificar(helper);
        NotificarHelper helperMozo = new NotificarHelper(EventoMensaje.CAMBIO_ESTADO_PEDIDO, null);
        notificar(helperMozo);
    }

    public List<Pedido> getPedidos() {
        return this.sistemaPedidos.getPedidos();
    }

    public void cambiarEstadoMesa(Mesa mesa) throws MesaException {
        this.sistemaPedidos.validarMesasConPedido(mesa);
    }

    public void confirmarServicio(Usuario mozo, Mesa mesaServ) {

        Usuario currentMozo = usuarioById(mozo.getUserId());
        Mesa currentMesa = currentMozo.obtenerMesaByNumero(mesaServ.getNumero());
        currentMesa.setPrecioServicio(currentMesa.getPrecioServicio());
        Cliente cliente = this.sistemaClientes.clienteById(mesaServ.getClienteServicio().getId());
        BeneficioCliente beneficioCli = null;
        ClienteRestaurant cliRest = null;
        String beneficioNombre = "";
        double descuento = 0;

        if (cliente != null) {
            int benficio = cliente.getBeneficio();
            switch (benficio) {
                case 1:
                    beneficioCli = new Comun();
                    beneficioNombre = "Café invitación";
                    break;
                case 2:
                    beneficioCli = new DeLaCasa();
                    beneficioNombre = "Descuento de $500";
                    break;
                case 3:
                    beneficioCli = new Preferencial();
                    beneficioNombre = "Agua invitación y descuento";
                    break;
            }

            if (beneficioCli != null) {
                cliRest = new ClienteRestaurant(beneficioCli);
                descuento = cliRest.getDescuento(currentMesa.getServicio());
            }

        }

        ServicioDTO servicioCli = new ServicioDTO();
        servicioCli.setTotalApagar(currentMesa.getPrecioServicio());
        servicioCli.setDescuentoServicio(descuento);
        servicioCli.setCostoServicio(currentMesa.getPrecioServicio() - descuento);

        if (cliente != null) {
            servicioCli.setNombreCliente(cliente.getNombre());        }
        this.sistemaUsuarios.confirmarServicio(mozo, mesaServ);

        NotificarHelper helperMozo = new NotificarHelper(EventoMensaje.LIMPIAR_SERVICIO, servicioCli);
        notificar(helperMozo);
    }

    public enum EVENTOS {
        RECIBIR_PEDIDO,
        TRANSFERIR_MESA,
        NUEVO_USUARIO_LOGUEADO,
        ACEPTAR_MESA,
        PEDIDOS_EN_ESPERA;
    };

    public Usuario login(String n, String p) throws CredencialesInvalidasException, UsuarioInactivoException, UsuarioLogueadoException {

        Usuario usuario = sistemaUsuarios.login(n, p);
        if (usuario != null) {
            agregarUsuarioLogueado(usuario);
            Fachada.getInstancia().notificar(Fachada.EVENTOS.NUEVO_USUARIO_LOGUEADO);
        }
        return usuario;
    }

    public void transferirMesa(MesaTransferida mesa) {
        mesa.setTipoMensaje(EventoMensaje.TRANSFERIR_MESA);
        notificar(mesa);
    }

    public void aceptarMesaTransf(MesaTransferida mesa) {
        if (mesa.isAceptaMesa()) {
            this.sistemaUsuarios.elimiarMesa(mesa);
            this.sistemaUsuarios.agregarMesa(mesa);
        }
        mesa.setTipoMensaje(EventoMensaje.ACEPTAR_MESA);
        Fachada.getInstancia().notificar(mesa);
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

    public List<Producto> actualizarProductos(Producto prod) {
        return this.sistemaProductos.actualizarProductos(prod);
    }

    public List<Usuario> obtenerUsuariosLogueados() {
        return this.sistemaUsuarios.getUsuariosLogueados();
    }

    public Usuario usuarioById(String usuarioId) {
        return this.sistemaUsuarios.UsuarioById(usuarioId);
    }

    public UnidadProcesadora unidadProcByProd(Producto p) {
        return this.sistemaProductos.obtenerUpByProducto(p);
    }

    public Producto productoById(int id) {
        return this.sistemaProductos.obtenerProductoById(id);
    }

    public List<UnidadProcesadora> obtenerUnidades() {
        return this.sistemaUnidades.getUnidades();
    }

    public void agregarCliente(Cliente cliente) {
        this.sistemaClientes.agregarCliente(cliente);
    }

    public void cargar() {

        Usuario carlos = new Mozo("4", "user1", "pass", "carlos valdez");
        Usuario pedro = new Mozo("5", "user3", "pass", "pedro mendez");
        Usuario juan = new Gestor("2", "user2", "pass", "juan perez");
        Usuario jose = new Gestor("6", "user", "pass", "jose perez");

        Cliente maria = new Cliente(1, "Maria", 1);
        Cliente pablo = new Cliente(2, "Pablo", 2);
        Cliente silvia = new Cliente(3, "Silvia", 3);

        Mesa mesa1 = new Mesa(1);
        Mesa mesa2 = new Mesa(2);
        Mesa mesa3 = new Mesa(3);
        mesa3.setEstado(true);
        mesa2.setEstado(true);
        Mesa mesa4 = new Mesa(4);

        Mesa mesa5 = new Mesa(5);
        Mesa mesa6 = new Mesa(6);
        Mesa mesa7 = new Mesa(6);

        //unidad procesadora
        UnidadProcesadora cocina = new UnidadProcesadora(1, "Cocina");
        UnidadProcesadora bar = new UnidadProcesadora(2, "bar");

        //productos
        Producto prod1 = new Producto(1, "hamburguesa", 90.0, 100, cocina);
        Producto prod3 = new Producto(3, "papas", 80.0, 100, cocina);
        Producto prod2 = new Producto(2, "cerveza", 120.0, 100, bar);

        Producto prod5 = new Producto(5, "cafe", 110.0, 100, bar);
        Producto prod6 = new Producto(6, "agua", 90.0, 100, bar);

        //pedidos
        Pedido ped1 = new Pedido(152, prod1, 2, "MCDONALd", mesa3, carlos, EstadoPedido.EN_ESPERA);
        Pedido ped2 = new Pedido(153, prod2, 5, "MCDONALd", mesa3, carlos, EstadoPedido.EN_ESPERA);
        Pedido ped3 = new Pedido(154, prod3, 2, "MCDONALd", mesa3, carlos, EstadoPedido.PROCESADO);

        carlos.agregarMesa(mesa1);
        carlos.agregarMesa(mesa2);
        carlos.agregarMesa(mesa3);
        carlos.agregarMesa(mesa4);

        carlos.agregarMesa(mesa5);
        carlos.agregarMesa(mesa6);
        pedro.agregarMesa(mesa7);

        this.agregarUsuario(carlos);
        this.agregarUsuario(pedro);
        this.agregarUsuario(juan);
        this.agregarUsuario(jose);

        this.agregarProducto(prod1);
        this.agregarProducto(prod2);
        this.agregarProducto(prod3);
        this.agregarProducto(prod5);
        this.agregarProducto(prod6);

        this.agregarUnidad(bar);
        this.agregarUnidad(cocina);

        this.agregarCliente(silvia);
        this.agregarCliente(pablo);
        this.agregarCliente(maria);

        // this.agregarPedidoSistema(ped1);
        // this.agregarPedidoSistema(ped2);
        // this.agregarPedidoSistema(ped3);
    }
}
