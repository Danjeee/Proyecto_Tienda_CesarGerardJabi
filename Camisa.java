public class Camisa extends Ropa{
    private String tipoCierre;
    private String tipoManga;
    private String estampada;
    public Camisa(int codigo, String nombre, double precio, String marca, String descripcion, Material material,
            String talla, String color, String tipoCierre, String tipoManga, String estampada) {
        super(codigo, nombre, precio, marca, descripcion, material, talla, color);
        this.tipoCierre = tipoCierre;
        this.tipoManga = tipoManga;
        this.estampada = estampada;
    }
    public String getTipoCierre() {
        return tipoCierre;
    }
    public void setTipoCierre(String tipoCierre) {
        this.tipoCierre = tipoCierre;
    }
    public String getTipoManga() {
        return tipoManga;
    }
    public void setTipoManga(String tipoManga) {
        this.tipoManga = tipoManga;
    }
    public String getEstampada() {
        return estampada;
    }
    public void setEstampada(String estampada) {
        this.estampada = estampada;
    }
}
