// @author Joaquín Soro Castelló
// Clase auxiliar para ser utilizada en los ejercicios 4 y 5 de la unidad 1.

public class Usuario {
    private String nombre;
    private String home;
    private String rutaTrabajo;

    // Constructor por defecto; usará los valores del usuario activo durante la ejecución.
    public Usuario(){

        this.nombre = System.getProperty("user.name");
        this.home = "/home/"+nombre;
        this.rutaTrabajo = System.getProperty("user.dir");

    }

    // Método pasando un parámetro de nombre; no usa los valores del usuario activo durante la ejecución.
    public Usuario(String nombre) {
        this.nombre = nombre;
        this.home = "/home/"+nombre;
        this.rutaTrabajo = "";

    }

    // Método pasando dos parámetros: nombre y ruta de usuario; no usa los valores del usuario activo durante la ejecución.
    public Usuario(String nombre, String dir) {
        this.nombre = nombre;
        this.home = "/home/"+nombre;
        this.rutaTrabajo = dir;

    }

    public String getNombre () { return nombre; }
    public void setNombre (String nombre) { this.nombre = nombre; }
    public String getHome () { return home; }
    public void setHome (String home) { this.home = home; }
    public String getRuta () { return this.rutaTrabajo; }
    public void setRuta (String ruta) { this.rutaTrabajo = ruta; }

}
