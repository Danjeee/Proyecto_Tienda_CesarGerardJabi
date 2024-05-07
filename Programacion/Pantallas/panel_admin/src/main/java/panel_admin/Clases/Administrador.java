package panel_admin.Clases;

import java.time.LocalDate;

public class Administrador extends Usuario{
    
    public boolean tienePrivilegios;
    private String rol;
    private int nivelAcceso;
    private Departamento departamento;

    public Administrador(String dni, String nombre, String apellidos, int telefono, String fecha_nacimiento,
            String direccion, String email, boolean tienePrivilegios, String rol, int nivelAcceso, Departamento departamento) {
        super(dni, nombre, apellidos, telefono, fecha_nacimiento, direccion, email);
        this.tienePrivilegios = tienePrivilegios;
        this.rol = rol;
        this.nivelAcceso = nivelAcceso;
        this.departamento = departamento;
        departamento.añadirAdmin(this.getDni(), this);
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
    public Departamento getDepartamento() {
        return departamento;
    }
    public void cambiarDepartamento(Departamento dep){
        this.departamento.eliminarAdmin(this.getDni());
        this.departamento = dep;
        this.departamento.añadirAdmin(this.getDni(), this);
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }
    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }    

}
