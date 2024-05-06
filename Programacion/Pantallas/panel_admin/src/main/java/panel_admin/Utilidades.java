package panel_admin;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import panel_admin.Clases.Articulo;
import panel_admin.Clases.Material;

public class Utilidades {

    public static Articulo obtenArt(TextField nombre, TextField precio, TextField marca, TextArea descripcion, CheckBox activo, TextField nombre_imagen, ChoiceBox<Integer> material) {
        
        Articulo art;

        String nom;
        double pre;
        String marc;
        String descrip;
        boolean activ = activo.isSelected();
        String nom_imagen;
        Integer mater;


        nom = nombre.getText();
        pre = Double.parseDouble(precio.getText());
        marc = marca.getText();
        descrip = descripcion.getText();
        nom_imagen = nombre_imagen.getText();
        mater = material.getValue();

        
        art = new Articulo("", nom, pre, marc, descrip, activ, nom_imagen, mater);

        return art;
    }
    public static boolean valida() {
        return true;
    }
}
