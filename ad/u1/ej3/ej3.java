/*
@author Joaquín Soro Castelló

Ejercicio 3. Basándote en los apuntes y ejemplos de

    http://chuwiki.chuidiang.org/index.php?title=Leer_y_modificar_fichero_de_propiedades_en_java

crea un fichero de propiedades con el siguiente contenido:

#Fichero de configuración de la aplicación X

version=1.2.3
lanzamiento=11/08/2021
standalone=yes
port=5858

Posteriormente el programa cargará estas propiedades, las modificará y actualizará a fichero modificando la fecha y la versión.
*/

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ej3 {

    public static void main(String[] args) {

        try {

            // Primero creamos el fichero properties_ej3.props en disco.

            Properties ej3conf = new Properties();
            ej3conf.setProperty("version", "1.2.3");
            ej3conf.setProperty("lanzamiento", "11/08/2021");
            ej3conf.setProperty("standalone", "yes");
            ej3conf.setProperty("port", "5858");

            try{
                ej3conf.store(new FileOutputStream("properties_ej3.props"), "Fichero de configuración de la aplicación X");
            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            // A continuación, leemos los datos desde el fichero properties_ej3.props.

            Properties leer = new Properties();
            try {
                leer.load(new FileReader("properties_ej3.props"));

            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

                String propsOriginal = "\nPROPIEDADES LEIDAS DESDE properties_ej3.props\n"+
                                    "-------------------------------------\n"
                                    +"version = "+leer.getProperty("version")
                                    +"\n"+"lanzamiento = "+leer.getProperty("lanzamiento")
                                    +"\n"+"standalone = "+leer.getProperty("standalone")
                                    +"\n"+"port = "+leer.getProperty("port")+"\n"
                                    +"-------------------------------------";

            // Modificamos la fecha por la fecha actual, y la versión por 9.9.9

            LocalDate fecha = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            leer.setProperty("version", "9.9.9");
            leer.setProperty("lanzamiento", fecha.format(formatter));

            String propsModificadas = "\nPROPIEDADES MODIFICADAS EN properties_ej3.props\n"+
                                    "-------------------------------------\n"
                                    +"version = "+leer.getProperty("version")
                                    +"\n"+"lanzamiento = "+leer.getProperty("lanzamiento")
                                    +"\n"+"standalone = "+leer.getProperty("standalone")
                                    +"\n"+"port = "+leer.getProperty("port")+"\n"
                                    +"-------------------------------------\n";

            // Guardamos las modificaciones

            leer.store(new FileWriter("properties_ej3.props"), "fichero modificado");

            // Mostramos el fichero original y los cambios realizados

            System.out.println(propsOriginal);
            System.out.println(propsModificadas);


            } catch (FileNotFoundException fnfe ) {
                fnfe.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
    }
}
