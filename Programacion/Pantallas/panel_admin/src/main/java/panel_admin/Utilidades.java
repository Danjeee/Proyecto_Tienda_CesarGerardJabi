package panel_admin;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import panel_admin.Clases.Articulo;
import panel_admin.Clases.Material;

public class Utilidades {

    public static Articulo obtenArt(TextField nombre, TextField precio, ChoiceBox marca, TextField descripcion, CheckBox activo, Button nombre_imagen, ChoiceBox material) {
        Articulo art;
        String nom;
        double pre;
        String marc;
        String descrip;
        Material mater;
        boolean activ;
        String nom_imagen;

        nom = nombre.getText();
        pre = Double.parseDouble(precio.getText());
        marc =
        descrip = descripcion.getText();
        mater =
        activ = activo.isActivo();
        nom_imagen = nombre_imagen.getText();


        art = new Articulo(nom, pre, marc, descrip, mater, activ, nom_imagen);

        return art;
    }
    public static boolean valida() {
        return true;
    }
}