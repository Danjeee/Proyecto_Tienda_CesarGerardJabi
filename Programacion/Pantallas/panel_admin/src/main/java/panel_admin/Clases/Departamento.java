package panel_admin.Clases;

import java.util.HashMap;
import java.util.Map;

public class Departamento {
    private int codigo;
    private String nombre;
    private Map<String, Empleado> admins = new HashMap<>();
    public Departamento(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public Map<String, Empleado> getAdmins() {
        return admins;
    }
    public void setAdmins(Map<String, Empleado> admins) {
        this.admins = admins;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void añadirAdmin(String dni, Empleado admin){
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
            String nombre = String.valueOf(this.codigo);
            String code = "";
            for (int i = 0; i < nombre.length(); i++) {
                code += nombre.charAt(i);
            }
            return Integer.parseInt(code);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Departamento dep = (Departamento) obj;
        return codigo == dep.codigo;
    }
}
