import java.util.List;

public class Clientes extends Usuario{
    
    private String cuenta;
    private int numPedidos;
    private String direccionEnvio;
    private boolean registrado;
    private List<MetodoPago> metodoPago;
    private Clientes cliente;

    
    public Clientes(String dni, String nombre, String apellidos, int telefono, String fechaNacimiento, String direccion,
            String email, String cuenta, int numPedidos, String direccionEnvio, boolean registrado,
            List<MetodoPago> metodoPago, Clientes cliente) {
        super(dni, nombre, apellidos, telefono, fechaNacimiento, direccion, email);
        this.cuenta = cuenta;
        this.numPedidos = numPedidos;
        this.direccionEnvio = direccionEnvio;
        this.registrado = registrado;
        this.metodoPago = metodoPago;
        this.cliente = cliente;
    }

    public String getCuenta() {
        return cuenta;
    }
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
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
    public boolean isRegistrado() {
        return registrado;
    }
    public void setRegistrado(boolean registrado) {
        this.registrado = registrado;
    }
    public List<MetodoPago> getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(List<MetodoPago> metodoPago) {
        this.metodoPago = metodoPago;
    }
    public Clientes getCliente() {
        return cliente;
    }
    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
}
