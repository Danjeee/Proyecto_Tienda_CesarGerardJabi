package tienda_javi_gerard_cesar.Clases;

import java.math.BigDecimal;

public class Articulo {

    private int codigo;
    private String nombre;
    private BigDecimal precio;
    private String marca;
    private int cant;
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    private String descripcion;
    private Material material;
    private String img;
    
    public Articulo(int codigo, String nombre, BigDecimal precio, String img) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.img = img;
    
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
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
    @Override
    public int hashCode() {
        return this.codigo;
    }
}