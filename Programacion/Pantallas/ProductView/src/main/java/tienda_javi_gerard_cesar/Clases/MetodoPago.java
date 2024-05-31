package tienda_javi_gerard_cesar.Clases;
public class MetodoPago {
    
    private int codigo;
    private String descripcion;

    
    public MetodoPago(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
            String nombre = String.valueOf(this.codigo);
            String code = "";
            for (int i = 0; i < nombre.length(); i++) {
                code += Integer.valueOf(nombre.charAt(i));
            }
            return Integer.parseInt(code);

    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MetodoPago metodo = (MetodoPago) obj;
        return String.valueOf(this.codigo).equals(String.valueOf(metodo.getCodigo()));
    }
    
}
