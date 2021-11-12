/*
@author Joaquín Soro Castelló

1. Realiza una aplicación que muestre las propiedades del usuario en el sistema.

*/

public class ej1
{
    public static void main(String[] args)
    {
    String user = System.getProperty("user.name");
    System.out.println("---------------------");
    System.out.println("El usuario actual es: "+user);
    System.out.println("El directorio /home de "+user+" es: " + System.getProperty("user.home"));
    System.out.println("La carpeta de trabajo actual es: " + System.getProperty("user.dir"));

    // La siguiente propiedad no es de usuario pero la he puesto igualmente.

    System.out.println("El sistema operativo en esta máquina es: " + System.getProperty("os.name")+" y la versión es: "+System.getProperty("os.version"));
    System.out.println("---------------------");

    }


}
