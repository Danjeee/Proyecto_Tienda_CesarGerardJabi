package com.example.basedatosempresafx;

import javafx.scene.control.TextField;

public class Utilidades {

    public static Articulo obtenArt(String id, TextField nombre, TextField precio, TextField codigo, TextField grupo) {
        Articulo art;
        String ide;
        String nom;
        float pre;
        String cod;
        int gru;

        ide = id;
        nom = nombre.getText();
        pre = Float.parseFloat(precio.getText());
        cod = codigo.getText();
        gru = Integer.parseInt(grupo.getText());

        if (ide.equalsIgnoreCase("")) {
            art = new Articulo("", nom, pre, cod, gru);
        } else {
            art = new Articulo(ide, nom, pre, cod, gru);
        }

        return art;
    }
    public static boolean valida() {
        return true;
    }
}
