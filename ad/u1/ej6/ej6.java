/* @author Joaquín Soro Castelló
6. Realiza una aplicación que, mediante RandomAccessFile, genere un fichero con datos de empleados de una empresa.
La posición ocupada por cada empleado en el fichero vendrá determinada por las 3 últimas cifras de su DNI.
Podrán haber sinónimos, resuélvelo con un área de excedentes al final del fichero. */

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;

class Fichero {
    // CONSTANTE (tamaño por defecto del fichero: 100 empleados)
    public static final int SIZE = Empleado.SIZE * 100;

    // CONSTANTE (nombre del archivo .dat)
    public static final String DAT = "empleados.dat";
    
    // VARIABLES
    public static int surplus = Fichero.SIZE; // posición mutable del área de excedentes: empleado 51
    public static int raflen = Fichero.SIZE; // posición mutable para controlar entre ciclos de ejecución

}
class Empleado {
    // CONSTANTES
    private static final int MAX_NOMBRE = 50; //50 chars = 100bytes
    public static final int SIZE = (Integer.SIZE/8)+(2 * MAX_NOMBRE+1)+(Integer.SIZE/8); //109 bytes

    // VARIABLES
    int pos; // posicion
    int salario; //32 bytes / 8 = 4byte
    String nombre;
    int dni; //dni de 8 numeros (evito la letra por resultar demasiado complicado)

    // CONSTRUCTOR CON PARÁMETROS
    public Empleado(int dni, String nom, int sal)
    {
        this.setDNI(dni);
        this.setSalario(sal);
        this.setPos(this.getDNI() % 100); // 99 * 109 < Fichero.SIZE
        this.setNombre(nom.length() > MAX_NOMBRE ? nom.substring(0,MAX_NOMBRE) : nom);
    }
    // MÉTODOS DE CLASE
    public int getPos() { return this.pos; } // 2 últimas cifras DNI = posicionador
    public void setPos(int p) { this.pos = p; }
    public String getNombre() { return this.nombre; }
    public void setNombre(String s) { this.nombre = s; }
    public int getDNI() { return this.dni; }
    public void setDNI(int s) { this.dni = s; }
    public int getSalario() { return this.salario; }
    public void setSalario(int s) { this.salario = s; }
    public String toString() throws NullPointerException
    {
        return "Empleado con ID "+(this.getPos())+"\n\t-Nombre: "+
        this.getNombre()+"\n\t-DNI: "+this.getDNI()+"\n\t-Salario: "+
        this.getSalario()+"\n";
    }

    // MÉTODO PARA ESCRIBIR EN UN RAF
    public void writeEmpleado(RandomAccessFile raf)
	{
		try
		{
			// posicionar según la pos del empleado
            int pos = this.getPos() * Empleado.SIZE;
            int dni;
			raf.seek(pos);
            dni = raf.readInt();

            if (dni != 0) {  // Ya hay un empleado
                System.out.println("Identificador duplicado, añadiendo empleado al área de excedentes.");
                pos = Fichero.surplus; // Añadir a excedentes
                raf.seek(pos);

                raf.writeInt(this.getDNI());
                raf.writeBytes(this.getNombre()+"\n");
                raf.writeInt(this.getSalario());

                Fichero.surplus = (int) raf.getFilePointer(); // Actualizar posición excedentes
                Fichero.raflen = (int) raf.length();        // Actualizar longitud del raf

            } else raf.seek(pos); //volver 
			// escribir los datos del empleado
			raf.writeInt(this.getDNI());
            raf.writeBytes(this.getNombre()+"\n");
            raf.writeInt(this.getSalario());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

    public boolean existeEmpleado(RandomAccessFile raf) {
        // Necesitamos esto para poder llamar de forma segura al método findEmpleado sin que estalle la ejecución.
        // Método no estático; se invoca sobre la instancia Empleado que inicializamos con valores nulos antes de la llamada a esta función.

        try
		{
			// posicionar según la pos del empleado
            int pos = this.getPos() * Empleado.SIZE;
			raf.seek(pos);
            int dni = this.getDNI();
            int dni_leer = raf.readInt();

            if (dni_leer == dni) { 

                return true;

            } else { // comprobar si existe como excedente

                raf.seek(Fichero.surplus);

                dni_leer = raf.readInt();

                while (dni_leer != dni && pos < raf.length()) {
                    pos += Empleado.SIZE;
                    raf.seek(pos);
                    dni_leer = raf.readInt();
                }

            }
		}
		catch (EOFException eof)
		{
			System.out.println("No se ha encontrado ningún empleado con ese DNI en el fichero");
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
        return false;  

    }
    public static Empleado findEmpleado(int dni, RandomAccessFile raf) // Teniendo el DNI, busca en el fichero e importa otros datos
    {   
        // Este codigo estalla si intentamos encontrar un empleado que no existe. Necesita llamada a existeEmpleado
        Empleado e = new Empleado(dni, "", -1);
        try
		{
			// posicionar según la pos del empleado
            int pos = e.getPos() * Empleado.SIZE;
			raf.seek(pos);
            int dni_leer = raf.readInt();

            if (dni_leer != 0) {

                e.setNombre(raf.readLine());
                e.setSalario(raf.readInt());

                //return e.toString();
                //return e;
            } else {

                raf.seek(Fichero.surplus);

                dni_leer = raf.readInt();

                while (dni_leer != dni && pos < raf.length()) {
                    pos += Empleado.SIZE;
                    raf.seek(pos);
                    dni_leer = raf.readInt();
                }
                e.setNombre(raf.readLine());
                e.setSalario(raf.readInt());
            }
		}
		catch (EOFException eof)
		{
            eof.printStackTrace();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
        return e;
    }
}

public class ej6 {
    public static void main(String[] args) throws FileNotFoundException, IOException{

        try {
          
            Scanner ent=new Scanner(System.in);

            ProcessBuilder pb = new ProcessBuilder("clear");
            Process startProcess;
            boolean repetir = true; // variable para continuar la ejecución del bucle while externo.

            File datos = new File(Fichero.DAT);

            while (repetir) {                   // MENU PRINCIPAL

                RandomAccessFile raf = new RandomAccessFile(datos, "rw");
                raf.setLength(Fichero.raflen);
                
                System.out.println("--------------------------------\nMenú principal\n--------------------------------"+
                "\n\t1. Introducir nuevo empleado\n\t2. Modificar empleado (buscar por dni)\n\t3. Mostrar empleados del fichero"+
                "\n\t4. Salir\n--------------------------------\n"+"> ");
                ent.reset();
                int opcion = Integer.valueOf(ent.nextLine());
                
                switch (opcion) {               // OPCIONES DEL MENU PRINCIPAL
                    case 1: {
                        try{
                            startProcess = pb.inheritIO().start();
                            startProcess.waitFor();
                        
                        } catch(Exception ex){
                            ex.printStackTrace();
                        }
                        
                        System.out.print("\nNUEVO EMPLEADO\n--------------------------------\nIntroduce DNI: "+"\n> ");
                        try {

                            int id = Integer.valueOf(ent.nextLine());

                            System.out.print("Introduce nombre: "+"\n> ");
                            String n = ent.nextLine();
    
                            System.out.print("Introduce salario: "+"\n> ");
                            int s = Integer.valueOf(ent.nextLine());
    
                            System.out.println("--------------------------------\n");
                            Empleado e = new Empleado(id, n, s);

                            e.writeEmpleado(raf);
                            System.out.println("\nEmpleado añadido con id: "+e.getPos()+".\n Pulsa Enter para continuar.");
                            ent.nextLine();                         

                        } catch (NumberFormatException e) { System.out.println("\n¡Atención! Introduce valores válidos:\n"+
                        " dni: cifra, nombre: palabras, salario: cifra)");}
                        break;
                    }    
                    case 2: {
                        try{
                            startProcess = pb.inheritIO().start();
                            startProcess.waitFor();
                        
                        } catch(Exception ex){
                            ex.printStackTrace();
                        }
                        System.out.println("\nMODIFICAR EMPLEADO\n--------------------------------\nIntroduce DNI: "+"\n> ");

                        int buscar = Integer.valueOf(ent.nextLine());

                        System.out.println("Procesando...\n");
                        Empleado x = new Empleado(buscar, "", -1);

                        if(x.existeEmpleado(raf)) {

                            x = Empleado.findEmpleado(buscar, raf);
                            System.out.println(x.toString());
                            modificar(x, raf);

                        }
                        System.out.println("No existe ese empleado en el fichero.");                        
                        break;
                    }                
                    case 3: {
                        // try{
                        //     startProcess = pb.inheritIO().start();
                        //     startProcess.waitFor();
                        
                        // } catch(Exception ex){
                        //     ex.printStackTrace();
                        // }
                        mostrar(raf);
                        System.out.println("\nPulsa Enter para continuar.");
                        ent.nextLine();
                    } break;
                    case 4: {
                        repetir = false;
                        raf.close();
                        System.out.println();
                        System.exit(0);                    
                    } break;
                    
                }

                try{
                    startProcess = pb.inheritIO().start();
                    startProcess.waitFor();
                
                } catch(Exception ex){
                    ex.printStackTrace();
                }
        
            }ent.close();

        } catch (FileNotFoundException fnfe) { fnfe.printStackTrace();} catch (FileAlreadyExistsException fae) {fae.printStackTrace();}

    }

    public static void mostrar (RandomAccessFile raf) throws EOFException {
        // La clave es que siempre que introducimos datos usamos pos * Empleado.SIZE
        // Esto implica que podemos leer secuencialmente desde el 0 hasta el final del fichero
        // Puesto que la posición de cada empleado, pese a no ser secuencial, si está condicionada
        // Por Empleado.SIZE (mediante esa multiplicación hacemos "batches" de ese tamaño que podemos procesar secuencialmente)
        int pos = 0;
        int dni_leer;

        System.out.println("\nLEYENDO FICHERO...\n");

        System.out.println("\nEmpleados del fichero: \n");
            
            try {

                raf.seek(pos); 

                while (pos < Fichero.SIZE) { // leer empleados del fichero
    
                    dni_leer = raf.readInt();

                    if (dni_leer != 0) {

                        Empleado n = new Empleado(dni_leer,"",-1);
                        n.setNombre(raf.readLine());
                        n.setSalario(raf.readInt());

                        System.out.println("\n\t"+n.toString());

                    } 
                    pos += Empleado.SIZE;
                    raf.seek(pos);                 
                }

                System.out.println("\nEmpleados en área de EXCEDENTES: \n");
                
                pos = Fichero.surplus;

                while (pos < raf.length()) { // leer excedentes del fichero

                    try {
    
                        raf.seek(pos);
                        dni_leer = raf.readInt();
    
                        if (dni_leer != 0) {
    
                            Empleado n = new Empleado(dni_leer,"",-1);
                            n.setNombre(raf.readLine());
                            n.setSalario(raf.readInt());
    
                            if (n.getSalario() >= 0) { System.out.println("\n\t"+n.toString()); }
    
                        } pos += Empleado.SIZE;
    
                    } catch (EOFException eof) {eof.printStackTrace(); break;} 
                }

            } catch (IOException ioe) {ioe.printStackTrace();}
            

        if (pos == 0) { System.out.println("No hay empleados guardados en el fichero.");}
    }

    public static void borrar (RandomAccessFile raf, Empleado e) {

        // Escribe en rafcopia los datos del archivo raforiginal inicial exceptuando los del empleado a borrar. Lee los datos anteriores
        // a la posicion en que se encuentre ese empleado y después lee los datos posteriores a esa posición hasta que acabe el fichero.
        // Finalmente se reemplaza el fichero raforiginal por rafcopia, que debe contener todos los empleados menos el que hay que borrar.

        RandomAccessFile raforiginal = raf;
        File original = new File("empleados.dat"),
        copia = new File("empleados.dat" + ".tmp");      //empleados.dat.tmp   

        try {

            RandomAccessFile rafcopia = new RandomAccessFile(copia, "rw");
            int pos = e.getPos() * Empleado.SIZE;
            byte[] datos_prev, datos_post;

            raforiginal.seek(pos);

            if (raforiginal.readInt() == e.getDNI()) { // Empleado está en su posición correspondiente en el fichero

                datos_prev = new byte[pos]; // de 0 a la pos del empleado e
                raforiginal.seek(0);
                raforiginal.readFully(datos_prev); // raf >> byte[]

                rafcopia.write(datos_prev);
                raforiginal.seek(pos);

                datos_post = new byte[ (int) raforiginal.length() - Empleado.SIZE - pos ];

                raforiginal.seek(pos + Empleado.SIZE);
                raforiginal.readFully(datos_post);

                rafcopia.seek(rafcopia.getFilePointer() + Empleado.SIZE);
                rafcopia.write(datos_post);
            
			    original.delete();
			    copia.renameTo(original);

			    // System.out.print("\nEl "+e.toString()+"ha sido eliminado del fichero.");
                rafcopia.close();
            } else {                                    // Empleado no está en su posición; ver excedentes

                datos_prev = new byte[Fichero.SIZE];    // Leer 1 fichero entero                
                raforiginal.seek(0);
                raforiginal.readFully(datos_prev);

                rafcopia.write(datos_prev);

                for (int i = pos; pos < raforiginal.length(); pos += Empleado.SIZE) {

                    raforiginal.seek(i);

                    int dni = raforiginal.readInt();
                    String nombre = raforiginal.readLine();
                    int salario = raforiginal.readInt();

                    if (dni != e.getDNI()) {

                        rafcopia.writeInt(dni);
                        rafcopia.writeBytes(nombre + "\n");
                        rafcopia.writeInt(salario);

                    } else { 
                        i += Empleado.SIZE;
                    }
                }
                original.delete();
                copia.renameTo(original);

                // System.out.print("\nEl "+e.toString()+"ha sido eliminado del fichero.");
                rafcopia.close();
            }

        } catch (FileNotFoundException fnfe) { fnfe.printStackTrace();} catch (IOException ioe) { ioe.printStackTrace();}            
    }
    
    public static void modificar (Empleado e, RandomAccessFile raf) {
        /* Este método inicia un bucle secundario de ejecución para presentar un menú con opciones de modificación. Permite
            cambiar cualquier atributo de un objeto Empleado, además de eliminarlo del fichero.*/

        int opcion = 0;
        boolean repetir = true;
        Scanner ent2=new Scanner(System.in);
        ProcessBuilder pb = new ProcessBuilder("clear");
        Process startProcess;        
        
        while (repetir) {

            System.out.println("\nMODIFICAR EMPLEADO\n--------------------------------\n"+e.toString()+
            "\n\t1. Cambiar dni\n\t2. Cambiar nombre\n\t3. Cambiar salario\n\t4. Eliminar este empleado\n\t"+
            "5. Aplicar cambios y volver\n--------------------------------\n"+"\n> ");

            opcion = ent2.nextInt();
            ent2.nextLine();

            switch (opcion) {

                case 1:
                System.out.print("Introduce nuevo DNI: "+"\n> ");

                //e.setDNI(id); si hacemos esto borrar() no funciona bien. usamos dni_old
                break;
    
                case 2:
                System.out.print("Introduce nuevo nombre: "+"\n> ");
                String n = ent2.nextLine();

                e.setNombre(n);
                break;

                case 3:
                System.out.print("Introduce nuevo salario: "+"\n> ");
                int s = Integer.valueOf(ent2.nextLine());

                e.setSalario(s);
                break;

                case 4:
                System.out.print("¿Eliminar empleado? S/n: "+"\n> ");
                String c = ent2.nextLine();
                boolean control = false;

                while (!control) {

                    if (c.toLowerCase().equals("s")) {

                        borrar(raf, e);
                        
                        try {
                            
                            raf = new RandomAccessFile(Fichero.DAT, "rw");

                        } catch (IOException ioe) { ioe.printStackTrace();}
                        control = false;
                        return;
                    } else if (c.toLowerCase().equals("n")){
        
                        System.out.println("Cancelado.");
                        control = false;
                        break;
                    } else { System.out.println("Introduce únicamente [S]í o [n]o, por favor."+"\n>"); control = true;}
                } break;
    
                case 5:
                repetir = false;
                System.out.println();
                return;

            }
            Empleado x = e; // Copia que reemplazará al original

            borrar(raf,e);
            try {
                            
                raf = new RandomAccessFile(Fichero.DAT, "rw");

            } catch (IOException ioe) { ioe.printStackTrace();}
    
            //x.setPos(pos_old);            
            x.writeEmpleado(raf);
        }
        try{
            startProcess = pb.inheritIO().start();
            startProcess.waitFor();
        
        } catch(Exception ex){
            ex.printStackTrace();
        }
        // System.out.println("Empleado modificado.\n Pulsa Enter para continuar.");
        // ent2.nextLine();
        ent2.close();
        return;    
    }
}