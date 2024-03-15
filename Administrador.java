public class Administrador extends Usuario{
    
    private boolean tienePrivilegios;
    private String rol;
    private int nivelAcceso;

    public Administrador(String dni, String nombre, String apellidos, int telefono, String fecha_nacimiento,
            String direccion, String email, boolean tienePrivilegios, String rol, int nivelAcceso) {
        super(dni, nombre, apellidos, telefono, fecha_nacimiento, direccion, email);
        this.tienePrivilegios = tienePrivilegios;
        this.rol = rol;
        this.nivelAcceso = nivelAcceso;
    }

    public boolean isTienePrivilegios() {
        return tienePrivilegios;
    }
    public void setTienePrivilegios(boolean tienePrivilegios) {
        this.tienePrivilegios = tienePrivilegios;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public int getNivelAcceso() {
        return nivelAcceso;
    }
    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }    
}
