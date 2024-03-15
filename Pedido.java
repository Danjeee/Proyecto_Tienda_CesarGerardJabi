public class Pedido {
    
    private int numero;
    private String fecha;
    private String direccionEnvio;
    private String estado;
    
    public Pedido(int numero, String fecha, String direccionEnvio, String estado) {
        this.numero = numero;
        this.fecha = fecha;
        this.direccionEnvio = direccionEnvio;
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getDireccionEnvio() {
        return direccionEnvio;
    }
    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
