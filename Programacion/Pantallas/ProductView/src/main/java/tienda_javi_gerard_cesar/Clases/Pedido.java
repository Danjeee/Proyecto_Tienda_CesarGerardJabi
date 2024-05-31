package tienda_javi_gerard_cesar.Clases;


import java.util.ArrayList;

public class Pedido {
    private int num;
    private String fecha;
    private String dir;
    private String estado;
    private ArrayList<Articulo> productos;
    public Pedido(int num, String fecha, String dir, String estado) {
        this.num = num;
        this.fecha = fecha;
        this.dir = dir;
        this.estado = estado;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getDir() {
        return dir;
    }
    public void setDir(String dir) {
        this.dir = dir;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public ArrayList<Articulo> getProductos() {
        return productos;
    }
    public void setProductos(ArrayList<Articulo> productos) {
        this.productos = productos;
    }
}
