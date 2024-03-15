package Programacion.Articulos.Accesorios;

import Programacion.Articulos.Material;

public class Zapatos extends Accesorios{
    private int talla;
    private String tipoSuela;
    public Zapatos(int codigo, String nombre, double precio, String marca, String descripcion, Material material,
            String estilo, boolean personalizado, int talla, String tipoSuela) {
        super(codigo, nombre, precio, marca, descripcion, material, estilo, personalizado);
        this.talla = talla;
        this.tipoSuela = tipoSuela;
    }
    public int getTalla() {
        return talla;
    }
    public void setTalla(int talla) {
        this.talla = talla;
    }
    public String getTipoSuela() {
        return tipoSuela;
    }
    public void setTipoSuela(String tipoSuela) {
        this.tipoSuela = tipoSuela;
    }
}
