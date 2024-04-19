package Usuarios;

import java.util.HashSet;
import java.util.Set;

public abstract class Usuario {

    private String dni;
    private String nombre;
    private String apellidos;
    private int telefono;
    private String fechaNacimiento;
    private String direccion;
    private String email;
    private Set<MetodoPago> metodosPago = new HashSet<>();

    public Usuario(String dni, String nombre, String apellidos, int telefono, String fechaNacimiento, String direccion,
            String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.email = email;
    }
    public void añadirMetodoPago(MetodoPago a){
        this.metodosPago.add(a);
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}