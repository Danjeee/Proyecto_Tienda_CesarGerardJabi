package tienda_javi_gerard_cesar.Clases;

public class Cifrado {
    public static String shiftCifrado(String pass){
        String cifrado = "";
        String[] troll = {"9023jkeds?¿dskjjfsd", "{àsd+*]]]]sdadasdad", "ololosadajdsfnsv++ça954SD", "PdfjpKAjsnjNJNjik", "mecagomemeometirounpeo"};
        int j = 0;
        for (int i = 0; i<pass.length(); i++){
            if (j == troll.length) {
                j = 0;
            }
            cifrado += troll[j] + (pass.charAt(i)+22);
            j++;
        }
        return cifrado;
    }
}
