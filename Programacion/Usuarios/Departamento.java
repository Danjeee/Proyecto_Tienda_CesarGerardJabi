package Usuarios;

import java.util.HashMap;
import java.util.Map;

public class Departamento {
    private String codigo;
    private String nombre;
    private Map<String, Administrador> admins = new HashMap<>();
    public Departamento(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Map<String, Administrador> getAdmins() {
        return admins;
    }
    public void setAdmins(Map<String, Administrador> admins) {
        this.admins = admins;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void añadirAdmin(String dni, Administrador admin){
        admins.put(dni, admin);
    }
    public void eliminarAdmin(String dni){
        if (this.admins.containsKey(dni)) {
            this.admins.remove(dni);
        } else {
            System.out.println("No se ha encontrado el administrador con DNI: " +dni);
        }
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
        Departamento dep = (Departamento) obj;
        return codigo.equals(dep.codigo);
    }
}
