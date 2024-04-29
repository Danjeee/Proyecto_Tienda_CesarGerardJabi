package panel_admin;
public abstract class Articulo {

    private int cod_art;
    private String nombre;
    private double precio;
    private String marca;
    private String descripcion;
    private Material material;
    private boolean activo = true;
    private String nombre_imagen;

    public Articulo(int cod_art, String nombre, double precio, String marca, String descripcion, Material material,
            boolean activo, String nombre_imagen) {
        this.cod_art = cod_art;
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.descripcion = descripcion;
        this.material = material;
        this.activo = activo;
        this.nombre_imagen = nombre_imagen;
    }

    public int getCod_art() {
        return cod_art;
    }

    public void setCod_art(int cod_art) {
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
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
}
