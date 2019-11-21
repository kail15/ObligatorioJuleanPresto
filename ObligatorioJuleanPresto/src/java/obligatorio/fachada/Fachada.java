package obligatorio.fachada;

import java.util.List;
import obligatorio.exceptions.ClienteException;
import observer.Observable;
import obligatorio.exceptions.CredencialesInvalidasException;
import obligatorio.exceptions.MesaException;
import obligatorio.exceptions.PedidoException;
import obligatorio.exceptions.UsuarioInactivoException;
import obligatorio.exceptions.UsuarioLogueadoException;
import obligatorio.modelo.Cliente;
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
import obligatorio.modelo.SistemaMesa;
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
public final class Fachada extends Observable {

    private SistemaUsuarios sistemaUsuarios;
    private SistemaUnidadProcesadora sistemaUnidades;
    private SistemaProductos sistemaProductos;
    private SistemaPedidos sistemaPedidos;
    private SistemaClientes sistemaClientes;
    private SistemaMesa sistemaMesa;

    private static Fachada instancia;

    private Fachada() {
        sistemaUsuarios = new SistemaUsuarios();
        sistemaProductos = new SistemaProductos();
        sistemaUnidades = new SistemaUnidadProcesadora();
        sistemaPedidos = new SistemaPedidos();
        sistemaClientes = new SistemaClientes();
        sistemaMesa = new SistemaMesa();
        cargar();

        //carga desde la BD
        sistemaUsuarios.cargarDatos();
        sistemaClientes.cargarDatos();
        sistemaUnidades.cargarDatos();
        sistemaMesa.cargarDatos();
        sistemaProductos.cargarDatos();

        normalizarUnidadProducto();
        normalizarMozoMesas();
    }

    private void normalizarUnidadProducto() {
        List<Producto> productos = obtenerProductos();
        for (Producto p : productos) {
            UnidadProcesadora unidad = obtenerUnidadById(p.getUnidadProcesadora().getOid());
            p.setUnidadProcesadora(unidad);
        }
    }

    private void normalizarMozoMesas() {
        List<Mesa> mesas = obtenerMesas();
        for (Mesa m : mesas) {
            Usuario mozo = usuarioById(m.getMozoId());
            mozo.agregarMesa(m);
        }
    }

    //private void normalizarMesas
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
        Fachada.getInstancia().notificar(helper);
        //notificar(helper);
        //notificar(helper);
        NotificarHelper helperMozo = new NotificarHelper(EventoMensaje.CAMBIO_ESTADO_PEDIDO, p);
        Fachada.getInstancia().notificar(helperMozo);
        //notificar(helperMozo);
    }

    public List<Pedido> getPedidos() {
        return this.sistemaPedidos.getPedidos();
    }

    public void cambiarEstadoMesa(Mesa mesa) throws MesaException {
        this.sistemaPedidos.validarMesasConPedido(mesa);
    }

    public void confirmarServicio(Usuario mozoParam, Mesa mesaServparam) throws ClienteException {
        Usuario mozo = mozoParam;
        Mesa mesaServ = mesaServparam;

        Usuario currentMozo = usuarioById(mozo.getUserId());
        Mesa currentMesa = currentMozo.obtenerMesaByNumero(mesaServ.getNumero());
        currentMesa.setPrecioServicio(currentMesa.getPrecioServicio());
        Cliente cliente = this.sistemaClientes.clienteById(mesaServ.getClienteServicio().getId());
        String beneficioNombre = "";
        double descuento = 0;

        if (cliente != null) {
            descuento = cliente.getDescuento(currentMesa.getServicio());

            if (cliente.getBeneficioCliente() instanceof DeLaCasa) {
                beneficioNombre = "Descuento de $500";
            } else if (cliente.getBeneficioCliente() instanceof Comun) {
                beneficioNombre = "Café invitación";
            } else if (cliente.getBeneficioCliente() instanceof Preferencial) {
                beneficioNombre = "Agua invitación y descuento";
            }
        }

        ServicioDTO servicioCli = new ServicioDTO();
        servicioCli.setTotalApagar(currentMesa.getPrecioServicio());
        servicioCli.setDescuentoServicio(descuento);

        if (cliente != null) {
            servicioCli.setNombreCliente(cliente.getNombre());
            servicioCli.setBeneficioNombre(beneficioNombre);
        }

        ///peristencia de servivio
        double costoServicio = currentMesa.getPrecioServicio() - descuento;
        Servicio servicioBase = new Servicio();
        servicioBase.setMesa(mesaServ);
        servicioBase.setDescuento(descuento);
        servicioBase.setCliente(cliente);
        servicioBase.setCostoServicio(costoServicio);
        servicioBase.setPrecioTotal(currentMesa.getPrecioServicio());
        guardarServicio(servicioBase);

        ///fin persistencia de servicio
        this.sistemaUsuarios.confirmarServicio(mozoParam, mesaServparam);

        NotificarHelper helperMozo = new NotificarHelper(EventoMensaje.LIMPIAR_SERVICIO, servicioCli);
        notificar(helperMozo);
        NotificarHelper helperGestor = new NotificarHelper(EventoMensaje.LIMPIAR_PEDIDOS, "");
        notificar(helperGestor);
        // notificar(helperGestor);

    }

    public Usuario login(String n, String p) throws CredencialesInvalidasException, UsuarioInactivoException, UsuarioLogueadoException {

        Usuario usuario = sistemaUsuarios.login(n, p);
        if (usuario != null) {
            agregarUsuarioLogueado(usuario);
            NotificarHelper helper = new NotificarHelper(EventoMensaje.NUEVO_USUARIO_LOGUEADO, null);
            Fachada.getInstancia().notificar(helper);
        }
        return usuario;
    }

    public void transferirMesa(MesaTransferida mesa) {
        NotificarHelper helper = new NotificarHelper(EventoMensaje.TRANSFERIR_MESA, mesa);
        notificar(helper);
    }

    public void aceptarMesaTransf(MesaTransferida mesa) {
        if (mesa.isAceptaMesa()) {
            this.sistemaUsuarios.agregarMesa(mesa);
            this.sistemaUsuarios.elimiarMesa(mesa);

        }
        NotificarHelper helper = new NotificarHelper(EventoMensaje.ACEPTAR_MESA, mesa);
        Fachada.getInstancia().notificar(helper);
    }

    public void asignarMesa(int oidMozo, Mesa mesa) {
        sistemaUsuarios.asignarMesa(oidMozo, mesa);
    }

    public void guardarServicio(Servicio s) {
        this.sistemaMesa.guardarServicio(s);
    }

    public List<Producto> obtenerProductos() {
        return this.sistemaProductos.getProductos();
    }

    public List<Mesa> obtenerMesas() {
        return this.sistemaMesa.getMesas();
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

    public UnidadProcesadora obtenerUnidadById(int id) {
        return this.sistemaUnidades.getUnidadById(id);
    }

    public void agregarCliente(Cliente cliente) {
        this.sistemaClientes.agregarCliente(cliente);
    }

    public void cargar() {

        Usuario carlos = new Mozo("4", "user1", "pass", "carlos valdez");
        Usuario pedro = new Mozo("5", "user3", "pass", "pedro mendez");
        Usuario juan = new Gestor("2", "user2", "pass", "juan perez");
        Usuario jose = new Gestor("6", "user", "pass", "jose perez");

//        Cliente maria = new Cliente(1, "Maria", 1);
//        Cliente pablo = new Cliente(2, "Pablo", 2);
//        Cliente silvia = new Cliente(3, "Silvia", 3);

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

        /*  carlos.agregarMesa(mesa1);
        carlos.agregarMesa(mesa2);
        carlos.agregarMesa(mesa3);
        carlos.agregarMesa(mesa4);

        carlos.agregarMesa(mesa5);
        carlos.agregarMesa(mesa6);
        pedro.agregarMesa(mesa7);*/
        this.agregarUsuario(carlos);
        this.agregarUsuario(pedro);
        this.agregarUsuario(juan);
        this.agregarUsuario(jose);

        /* this.agregarProducto(prod1);
        this.agregarProducto(prod2);
        this.agregarProducto(prod3);
        this.agregarProducto(prod5);
        this.agregarProducto(prod6);*/
        this.agregarUnidad(bar);
        this.agregarUnidad(cocina);

//        this.agregarCliente(silvia);
//        this.agregarCliente(pablo);
//        this.agregarCliente(maria);

        // this.agregarPedidoSistema(ped1);
        // this.agregarPedidoSistema(ped2);
        // this.agregarPedidoSistema(ped3);
    }
}
