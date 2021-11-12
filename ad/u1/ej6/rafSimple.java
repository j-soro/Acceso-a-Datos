// Creado como repaso para el examen, un ejemplo sencillo de raf
import java.io.*;
import java.util.ArrayList;

class Empleado {

    private int dni; // 4 bytes
    private String nombre; // 2 * 50 = 100 bytes
    public static final int MAX_EMPLEADO = 105; //104 + 1 ("\n")
    public static final int MAX_STRING = 50;
    
    public int getDNI () {
        return this.dni;
    }

    public void setDNI (int i) {
        this.dni = i;
    }

    public String getNombre () {

        return this.nombre;
    }

    public void setNombre( String s) {

        this.nombre = s;
    }

    public String toString() {

        return "\n"+this.getNombre()+" "+this.getDNI()+" \n";
    }

    public Empleado(int dni, String nombre) {

        this.setDNI(dni);
        this.setNombre(nombre.length() > MAX_STRING ? nombre.substring(0,MAX_STRING) : nombre);
    }
}
/* 6. Realiza una aplicación que, mediante RandomAccessFile, genere un fichero con datos de empleados de una empresa.
La posición ocupada por cada empleado en el fichero vendrá determinada por las 3 últimas cifras de su DNI.
Podrán haber sinónimos, resuélvelo con un área de excedentes al final del fichero. */

public class rafSimple {

    public final static int MAX_FICHERO = Empleado.MAX_EMPLEADO * 1000;

    public static void main(String[] args) throws IOException {

        ArrayList<Empleado> lista = new ArrayList<>();

        Empleado e1 = new Empleado(12345678, "Francisco");
        Empleado e2 = new Empleado(22127359, "Pepe");
        Empleado e3 = new Empleado(46081569, "Luis");

        lista.add(e1); lista.add(e2); lista.add(e3);

        try ( RandomAccessFile raf = new RandomAccessFile("empleadosSimple.dat", "rw")) {

            raf.setLength(MAX_FICHERO);
        
            for (Empleado e : lista) {
    
                raf.seek(0);
                int pos = e.getDNI() % 1000 * Empleado.MAX_EMPLEADO;
                raf.seek(pos);
    
                int dni_leer = raf.readInt();
    
                if (dni_leer != 0) {
    
                    System.out.println("Empleado duplicado. Añadiendo a excedentes. ");
                    raf.seek(MAX_FICHERO);
                } else { raf.seek(pos); }
                
                raf.writeInt(e.getDNI());
                raf.writeBytes(e.getNombre() +"\n");
            }
        }
    }
}
