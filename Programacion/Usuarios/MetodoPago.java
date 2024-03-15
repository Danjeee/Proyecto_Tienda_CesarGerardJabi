package Programacion.Usuarios;
public class MetodoPago {
    
    private String codigo;
    private String descripcion;

    
    public MetodoPago(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
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
            String nombre = this.codigo;
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
        return codigo.equals(metodo.codigo);
    }
    
}
