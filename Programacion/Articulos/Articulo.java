package Articulos;
public abstract class Articulo {

    private int codigo;
    private String nombre;
    private double precio;
    private String marca;
    private String descripcion;
    private Material material;
    
    public Articulo(int codigo, String nombre, double precio, String marca, String descripcion, Material material) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.descripcion = descripcion;
        this.material = material;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
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
}