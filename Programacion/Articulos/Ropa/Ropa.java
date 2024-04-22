package Articulos.Ropa;

import Articulos.Articulo;
import Articulos.Material;

public abstract class Ropa extends Articulo{
    private String talla;
    private String color;
    public Ropa(int codigo, String nombre, double precio, String marca, String descripcion, Material material,
            String talla, String color) {
        super(codigo, nombre, precio, marca, descripcion, material);
        this.talla = talla;
        this.color = color;
    }
    public String getTalla() {
        return talla;
    }
    public void setTalla(String talla) {
        this.talla = talla;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
