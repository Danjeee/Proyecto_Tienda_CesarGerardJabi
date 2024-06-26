package Articulos.Accesorios;
import Articulos.Material;
import Articulos.Articulo;

public abstract class Accesorios extends Articulo{

    private String estilo;
    private boolean personalizado;
    
    public Accesorios(int codigo, String nombre, double precio, String marca, String descripcion, Material material, String estilo,
            boolean personalizado) {
        super(codigo, nombre, precio, marca, descripcion, material);
        this.estilo = estilo;
        this.personalizado = personalizado;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public boolean isPersonalizado() {
        return personalizado;
    }

    public void setPersonalizado(boolean personalizado) {
        this.personalizado = personalizado;
    }    
}
