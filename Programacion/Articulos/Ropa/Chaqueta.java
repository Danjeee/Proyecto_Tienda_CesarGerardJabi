package Articulos.Ropa;

import Articulos.Material;

public class Chaqueta extends Ropa{
    private String tipoCierre;
    private boolean impermeable;
    public Chaqueta(int codigo, String nombre, double precio, String marca, String descripcion, Material material,
            String talla, String color, String tipoCierre, boolean impermeable) {
        super(codigo, nombre, precio, marca, descripcion, material, talla, color);
        this.tipoCierre = tipoCierre;
        this.impermeable = impermeable;
    }
    public String getTipoCierre() {
        return tipoCierre;
    }
    public void setTipoCierre(String tipoCierre) {
        this.tipoCierre = tipoCierre;
    }
    public boolean isImpermeable() {
        return impermeable;
    }
    public void setImpermeable(boolean impermeable) {
        this.impermeable = impermeable;
    }
}
