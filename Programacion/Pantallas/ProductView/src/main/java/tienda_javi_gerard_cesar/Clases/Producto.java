package tienda_javi_gerard_cesar.Clases;
public class Producto {

    private String cod_art;
    private String nombre;
    private double precio;
    private String marca;
    private String descripcion;
    private boolean activo = true;
    private String nombre_imagen;
    private Material material;

    public Producto(String cod_art, String nombre, double precio, String marca, String descripcion, boolean activo,
            String nombre_imagen, Material material) {
        this.cod_art = cod_art;
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.descripcion = descripcion;
        this.activo = activo;
        this.nombre_imagen = nombre_imagen;
        this.material = material;
    }

    public String getCod_art() {
        return cod_art;
    }

    public void setCod_art(String cod_art) {
        this.cod_art = cod_art;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getNombre_imagen() {
        return nombre_imagen;
    }

    public void setNombre_imagen(String nombre_imagen) {
        this.nombre_imagen = nombre_imagen;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
    
    
    
}
