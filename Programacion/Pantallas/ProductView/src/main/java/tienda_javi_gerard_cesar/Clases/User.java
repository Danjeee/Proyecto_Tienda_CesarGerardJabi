package tienda_javi_gerard_cesar.Clases;

public class User {
    private String mail;
    private String pasw;
    private String DNI;
    private boolean act;
   
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPasw() {
        return pasw;
    }
    public void setPasw(String pasw) {
        this.pasw = pasw;
    }
    public User(String mail, String pasw, String dNI, boolean act) {
        this.mail = mail;
        this.pasw = pasw;
        this.DNI = dNI;
        this.act = act;
    }
    public String getDNI() {
        return DNI;
    }
    public void setDNI(String dNI) {
        DNI = dNI;
    }
    public boolean isAct() {
        return act;
    }
    public void setAct(boolean act) {
        this.act = act;
    }
}
