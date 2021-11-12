package com.dam2.accesodatos;

import java.util.ArrayList;

public class ej7 {

    public static void main (String[] args) {

        VentanaModel modelo = new VentanaModel();
        Ventana vista = new Ventana();
        VentanaController controlador = new VentanaController(modelo, vista);

    }


}
