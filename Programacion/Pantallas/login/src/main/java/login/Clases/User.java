package login.Clases;

public class User {
    private String mail;
    private String pasw;
    private String DNI;
   
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
    public User(String mail, String pasw, String dNI) {
        this.mail = mail;
        this.pasw = pasw;
        DNI = dNI;
    }
    public String getDNI() {
        return DNI;
    }
    public void setDNI(String dNI) {
        DNI = dNI;
    }
}
