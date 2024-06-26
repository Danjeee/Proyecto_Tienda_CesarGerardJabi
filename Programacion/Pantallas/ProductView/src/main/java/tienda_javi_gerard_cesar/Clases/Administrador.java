package tienda_javi_gerard_cesar.Clases;

import java.time.LocalDate;

public class Administrador extends Usuario{
    
    public boolean tienePrivilegios;
    private boolean activo;
    private String pass;
    private Departamento departamento;

    public Administrador(String dni, String nombre, String apellidos, String telefono, String fechaNacimiento,
            String direccion, String email, boolean tienePrivilegios, boolean activo, String pass,
            Departamento departamento) {
        super(dni, nombre, apellidos, telefono, fechaNacimiento, direccion, email);
        this.tienePrivilegios = tienePrivilegios;
        this.activo = activo;
        this.pass = pass;
        this.departamento = departamento;
    }

    public boolean isTienePrivilegios() {
        return tienePrivilegios;
    }
    public void setTienePrivilegios(boolean tienePrivilegios) {
        this.tienePrivilegios = tienePrivilegios;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public Departamento getDepartamento() {
        return departamento;
    }
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

   
}
