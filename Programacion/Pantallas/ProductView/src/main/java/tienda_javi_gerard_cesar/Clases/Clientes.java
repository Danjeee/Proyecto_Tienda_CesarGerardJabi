package tienda_javi_gerard_cesar.Clases;
import java.util.HashSet;
import java.util.Set;

public class Clientes extends Usuario{
    
    private String pass;
    private float saldoCuenta;
    private int numPedidos;
    private String direccionEnvio;
    private boolean tarjeta_fidelizacion;
    private boolean activo;
    private MetodoPago metodoPago;

    public Clientes(String dni, String nombre, String apellidos, String telefono, String fechaNacimiento,
            String direccion, String email, String pass, float saldoCuenta, int numPedidos, String direccionEnvio,
            boolean tarjeta_fidelizacion, boolean activo, MetodoPago metodoPago) {
        super(dni, nombre, apellidos, telefono, fechaNacimiento, direccion, email);
        this.pass = pass;
        this.saldoCuenta = saldoCuenta;
        this.numPedidos = numPedidos;
        this.direccionEnvio = direccionEnvio;
        this.tarjeta_fidelizacion = tarjeta_fidelizacion;
        this.activo = activo;
        this.metodoPago = metodoPago;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public float getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setSaldoCuenta(float saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    public int getNumPedidos() {
        return numPedidos;
    }

    public void setNumPedidos(int numPedidos) {
        this.numPedidos = numPedidos;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public boolean isTarjeta_fidelizacion() {
        return tarjeta_fidelizacion;
    }

    public void setTarjeta_fidelizacion(boolean tarjeta_fidelizacion) {
        this.tarjeta_fidelizacion = tarjeta_fidelizacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    
    

    
}
