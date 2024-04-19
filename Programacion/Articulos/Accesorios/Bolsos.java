package Articulos.Accesorios;

import Articulos.Material;

public class Bolsos extends Accesorios {
    
    private String tipoDeCierre;
    private double capacidadDelBolso;
    
    public Bolsos(int codigo, String nombre, double precio, String marca, String descripcion, Material material, String estilo,
            boolean personalizado, String tipoDeCierre, double capacidadDelBolso) {
        super(codigo, nombre, precio, marca, descripcion, material, estilo, personalizado);
        this.tipoDeCierre = tipoDeCierre;
        this.capacidadDelBolso = capacidadDelBolso;
    }

    public String getTipoDeCierre() {
        return tipoDeCierre;
    }

    public void setTipoDeCierre(String tipoDeCierre) {
        this.tipoDeCierre = tipoDeCierre;
    }

    public double getCapacidadDelBolso() {
        return capacidadDelBolso;
    }

    public void setCapacidadDelBolso(double capacidadDelBolso) {
        this.capacidadDelBolso = capacidadDelBolso;
    }

    
}
