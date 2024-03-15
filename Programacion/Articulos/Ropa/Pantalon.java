package Programacion.Articulos.Ropa;

import Programacion.Articulos.Material;

public class Pantalon extends Ropa{
    private String tipoCierre;
    private boolean tieneBolsillos;
    private String tipoPantalon;
    public Pantalon(int codigo, String nombre, double precio, String marca, String descripcion, Material material,
            String talla, String color, String tipoCierre, boolean tieneBolsillos, String tipoPantalon) {
        super(codigo, nombre, precio, marca, descripcion, material, talla, color);
        this.tipoCierre = tipoCierre;
        this.tieneBolsillos = tieneBolsillos;
        this.tipoPantalon = tipoPantalon;
    }
    public String getTipoCierre() {
        return tipoCierre;
    }
    public void setTipoCierre(String tipoCierre) {
        this.tipoCierre = tipoCierre;
    }
    public boolean isTieneBolsillos() {
        return tieneBolsillos;
    }
    public void setTieneBolsillos(boolean tieneBolsillos) {
        this.tieneBolsillos = tieneBolsillos;
    }
    public String getTipoPantalon() {
        return tipoPantalon;
    }
    public void setTipoPantalon(String tipoPantalon) {
        this.tipoPantalon = tipoPantalon;
    }
}
