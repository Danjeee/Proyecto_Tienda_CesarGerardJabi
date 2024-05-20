package tienda_javi_gerard_cesar.Clases;

public class CuentaPago {
    private int id;
    private String cuenta;
    private String fecha;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCuenta() {
        return cuenta;
    }
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public CuentaPago(int id, String cuenta, String fecha) {
        this.id = id;
        this.cuenta = cuenta;
        this.fecha = fecha;
    }
}
