package tienda_javi_gerard_cesar.Clases;

public class Descuento {
    private String nombre;
    private int cantidad;
    private Boolean freeShip;
    private String usablepor;
    public Descuento(String nombre, int cantidad, Boolean freeShip, String usablepor) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.freeShip = freeShip;
        this.usablepor = usablepor;
    }
    public String getUsablepor() {
        return usablepor;
    }
    public void setUsablepor(String usablepor) {
        this.usablepor = usablepor;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public Boolean getFreeShip() {
        return freeShip;
    }
    public void setFreeShip(Boolean freeShip) {
        this.freeShip = freeShip;
    }
    @Override
    public String toString() {
        return this.nombre;
    }
}
