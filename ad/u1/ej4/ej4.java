/*
@author Joaquín Soro Castelló

4. Realiza la aplicación que genere un fichero XML con datos de personas como en el ejercicio 1.

*/

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

public class ej4 {

    public static void main(String[] args) throws ParserConfigurationException,TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        Element raiz = documento.createElement("usuarios");
        documento.getDocumentElement().appendChild(raiz);
        Element nodoUsuario = null , nodoDatos = null ;
        Text texto = null;

        // Vamos a utilizar una clase Usuario, que se encuentra en el mismo directorio que ej4. Es un análogo de la clase Producto.

        // Para este ejercicio he pensado crear 5 usuarios distintos. Algunos tienen un directorio y otros no. El valor por defecto para un directorio vacío será "/home/<nombreusuario>".

        // Usuarios: legendre, gauss, poisson, bernouilli, rutherford

        // Crearemos una lista de usuarios compuesta por objetos de la clase Usuario.

        // IMPORTANTE: Algunos usuarios tienen un directorio de trabajo definido y otros no, depende de la llamada al constructor.

        Usuario u1 = new Usuario("legendre", "/home/legendre/Documentos/");
        Usuario u2 = new Usuario("gauss", "/opt/android-studio/bin/");
        Usuario u3 = new Usuario("poisson", "/etc/alsa/conf.d/");
        Usuario u4 = new Usuario("bernouilli", "/media/Data/Music/");
        Usuario u5 = new Usuario("rutherford", "/usr/bin/X11/");

        Vector<Usuario> listaUsuarios = new Vector<Usuario>();

        listaUsuarios.add(u1);
        listaUsuarios.add(u2);
        listaUsuarios.add(u3);
        listaUsuarios.add(u4);
        listaUsuarios.add(u5);

        for (Usuario usuario : listaUsuarios) {
            nodoUsuario = documento.createElement("usuario");
            raiz.appendChild(nodoUsuario);
            nodoDatos = documento.createElement("nombre");
            nodoUsuario.appendChild(nodoDatos);
            texto = documento.createTextNode(usuario.getNombre());
            nodoDatos.appendChild(texto);
            nodoDatos = documento.createElement("home");
            nodoUsuario.appendChild(nodoDatos);
            texto = documento.createTextNode(usuario.getHome());
            nodoDatos.appendChild(texto);
            nodoDatos = documento.createElement("dir");
            nodoUsuario.appendChild(nodoDatos);
            texto = documento.createTextNode(usuario.getRuta());
            nodoDatos.appendChild(texto);
        }

        // Generamos el fichero XML con la información de cada usuario presente en el Vector listaUsuarios.

        // NOTA: El navegador web es capaz de abrir el archivo XML y generar las indentaciones automáticamente.

        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(new File("usuarios_ej4.xml"));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source,resultado);

        System.out.println("Fichero usuarios_ej4.xml guardado en el directorio de trabajo");

    }

}
