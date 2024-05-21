package tienda_javi_gerard_cesar.Clases;

public class CuentaPago {
    private int id;
    private String tipo;
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
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public CuentaPago(int id, String cuenta, String fecha, String tipo) {
        this.id = id;
        this.cuenta = cuenta;
        this.fecha = fecha;
        this.tipo = tipo;
    }
    @Override
    public String toString() {
        if (tipo.equals("Tarjeta")) {
            return tipo +": " +cuenta.substring(0,3)+"********";
        } else {
            return tipo +": " +cuenta;
        }
    }
}
