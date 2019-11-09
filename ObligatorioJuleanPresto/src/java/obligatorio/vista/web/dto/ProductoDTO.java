
package obligatorio.vista.web.dto;


public class ProductoDTO {

    private int codigo;
    private String nombre;
    private double precioUnitario;
    private int stockDisponible;
    private UnidadProcesadoraDTO unidadProcesadora;

    public ProductoDTO(int codigo, String nombre, double precioUnitario, int stockDisponible, UnidadProcesadoraDTO unidadProcesadora) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.stockDisponible = stockDisponible;
        this.unidadProcesadora = unidadProcesadora;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public UnidadProcesadoraDTO getUnidadProcesadora() {
        return unidadProcesadora;
    }

    public void setUnidadProcesadora(UnidadProcesadoraDTO unidadProcesadora) {
        this.unidadProcesadora = unidadProcesadora;
    }   
    
}
