package tienda_javi_gerard_cesar.Clases;
public class Material {
    private String codigo;
    private String denominacion;
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDenominacion() {
        return denominacion;
    }
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
    public Material(String codigo, String denominacion) {
        this.codigo = codigo;
        this.denominacion = denominacion;
    }
}
