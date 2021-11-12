/*
@author Joaquín Soro Castelló

5. Realiza la aplicación que lea y muestre los datos del fichero XML creado por la anterior aplicación.

*/

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class ej5 {

    public static void main(String[] args) throws SAXException,IOException,ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse("usuarios_ej4.xml");

        NodeList usuarios = documento.getElementsByTagName("usuario");

        System.out.println("\n");
        for (int i = 0; i < usuarios.getLength(); i++) {
            Node usuario = usuarios.item(i) ;
            Element elemento = (Element) usuario ;

            /*
            item(0) == nombre; item(1) == home; item(2) == dir.

            NOTA: Si tuviésemos varios usuarios dentro de cada elemento <usuario> en nuestro xml, este código no funcionaría correctamente.
            Por otro lado, no tendría mucho sentido hacer así el fichero XML y el coste de computación se elevaría al necesitar otro bucle dentro de este bucle for.
            Para controlar si existen más hijos dentro de un item(x) podemos usar while (item(x).hasChildNodes()).
            */ 
            System.out.println("* Nombre de usuario: "+elemento.getElementsByTagName("nombre").item(0).getFirstChild().getTextContent());
            System.out.println("    - Carpeta de usuario: "+elemento.getElementsByTagName("home").item(0).getFirstChild().getTextContent());
            System.out.println("    - Directorio de trabajo: "+elemento.getElementsByTagName("dir").item(0).getFirstChild().getTextContent());
            System.out.println("\n------------------------------------------------------\n");

        }












    }














}
