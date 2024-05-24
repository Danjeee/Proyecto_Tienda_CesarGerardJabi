package login.Clases;

import java.time.LocalDate;

public class Empleado extends Usuario{
    
    private boolean activo;
    public boolean tienePrivilegios;
    private String pass;
    private Departamento departamento;

    
    public Empleado(String dni, String nombre, String apellidos, String telefono, String fechaNacimiento,
            String direccion, String email, boolean activo, boolean tienePrivilegios, String pass,
            Departamento departamento) {
        super(dni, nombre, apellidos, telefono, fechaNacimiento, direccion, email);
        this.activo = activo;
        this.tienePrivilegios = tienePrivilegios;
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

    public void setCant(int cantt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCant'");
    }

   
}
