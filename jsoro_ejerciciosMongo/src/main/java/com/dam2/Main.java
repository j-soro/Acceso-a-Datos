package com.dam2;

import java.util.Scanner;

public class Main {
    static Model model;
    static String userSession = null;
    public static void main(String[] args) {
        model = new Model();
        //model.deleteAllUsers();
        //model.generateUsers();

        menuPrincipal();
    }

    private static void menuPrincipal() {
        ClearConsole();
        int option = 0;
        Scanner sc = new Scanner(System.in);
        do
        {
            if (userSession != null)
            {
                System.out.println("\n----------------\nMenú principal\n----------------\n" +
                        "*. Identificado como: "+userSession+"\n" +
                        "2. Gestionar usuarios\n" +
                        "3. Ver todos los usuarios"+"\n" +
                        "4. Salir\n" +
                        "5. Cerrar sesión\n----------------\n");
            } else
            {
                System.out.println("\n----------------\nMenú principal\n----------------\n" +
                        "1. Acceder como usuario registrado\n" +
                        "2. Gestionar usuarios\n" +
                        "3. Ver todos los usuarios\n" +
                        "4. Salir\n----------------\n");
            }

            option = sc.nextInt();

            switch(option){
                case 1:
                    login();
                    break;
                case 2:
                    menuUsuarios();
                    break;
                case 3:
                    verTodos();
                    break;
                case 4:
                    break;
                case 5:
                    logout();
                    break;
            }

        }while(option != 4);
    }

    private static void logout() {
        System.out.println("\nHasta luego, "+ userSession+".");
        userSession = null;
        System.out.println("Sesión cerrada. Volviendo al menú principal.\n");
    }

    private static void login() {
        Scanner sc = new Scanner(System.in);
        Boolean control = false;
        do{
            System.out.println("Introduce tu nick: ");
            String username = sc.nextLine();

            System.out.println("Introduce tu contraseña: ");
            String password = sc.nextLine();

            try{
                if (model.userLogin(username, password))
                {
                    System.out.println("Bienvenido, "+username+".");
                    userSession = username;
                } else
                {
                    System.out.println("Credenciales incorrectas.");
                    control = true;
                    sc.nextLine();
                    break;
                }
                control = true;
                sc.nextLine();

            } catch (Exception ex){
                ex.printStackTrace();
            }
        } while (!control);
    }

    private static void verTodos() {
        Scanner sc = new Scanner(System.in);
        Boolean control = false;
        User user = new User();
        do{
            System.out.println("Mostrando usuarios: ");

            try{
                model.getAll();
                System.out.println("Terminado.");
                control = true;
                sc.nextLine();

            } catch (Exception ex){
                ex.printStackTrace();
            }
        } while (!control);
    }

    private static void menuUsuarios(){
        ClearConsole();
        int option = 0;
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println("\n----------------\nMenú de usuarios\n----------------\n" +
                    "1. Crear usuario nuevo\n" +
                    "2. Buscar usuario\n" +
                    "3. Modificar usuario\n" +
                    "4. Borrar usuario\n" +
                    "5. Salir\n----------------\n");

            option = sc.nextInt();

            switch(option){
                case 1:
                    crearUser();
                    break;
                case 2:
                    buscarUser();
                    break;
                case 3:
                    cambiarUser();
                    break;
                case 4:
                    borrarUser();
                    break;
                case 5:
                    break;
            }
        }while(option != 5);
    }
    private static void crearUser() {
        Scanner sc = new Scanner(System.in);
        Boolean control = false;
        User user = new User();
        do{
            System.out.println("Introduce nick: ");
            String username = sc.nextLine();

            System.out.println("Introduce password");
            String password = sc.nextLine();

            System.out.println("Introduce nombre: ");
            String firstname = sc.nextLine();

            System.out.println("Introduce apellido");
            String lastname = sc.nextLine();

            try{
                user.setUsername(username).setPassword(password).setFirstname(firstname).setLastname(lastname);
                model.createUser(user);

                System.out.println("Usuario creado");
                control = true;
                sc.nextLine();
            } catch (Exception ex){
                ex.printStackTrace();
            }

        } while (!control);
    }
    private static void buscarUser() {
        Scanner sc = new Scanner(System.in);
        Boolean control = false;
        User user = new User();
        do{
            System.out.println("Introduce el nick del usuario a encontrar: ");
            String username = sc.nextLine();
            try{
                user = model.findUserByUserName(username);

                System.out.println("Usuario encontrado:\n"+
                        "\n"+
                        "* Nombre:"+user.getFirstname()+"\n"+
                        "* Apellido:"+user.getLastname()+"\n"
                );

                control = true;
                sc.nextLine();
            } catch (Exception ex){
                System.out.println("Ese usuario no existe.");
                sc.nextLine();
                control = true;
                break;
            }

        } while (!control);
    }
    private static void cambiarUser() {
        Scanner sc = new Scanner(System.in);
        Boolean control = false;
        User user = new User();
        do{
            System.out.println("Introduce el nick del usuario a modificar: ");
            String username = sc.nextLine();

            System.out.println("Introduce nuevo nick, o el anterior: ");
            String newUsername = sc.nextLine();

            System.out.println("Introduce nuevo password, o el anterior: ");
            String password = sc.nextLine();

            System.out.println("Introduce nuevo nombre, o el anterior: ");
            String firstname = sc.nextLine();

            System.out.println("Introduce nuevo apellido, o el anterior: ");
            String lastname = sc.nextLine();

            try{
                user.setUsername(username).setPassword(password).setFirstname(firstname).setLastname(lastname);
                model.updateUser(user);
                user.setUsername(newUsername);

                System.out.println("Usuario modificado.");
                control = true;
                sc.nextLine();
            } catch (Exception ex){
                ex.printStackTrace();
            }

        } while (!control);
    }
    private static void borrarUser() {
        Scanner sc = new Scanner(System.in);
        Boolean control = false;
        do{
            System.out.println("Introduce el nick del usuario a eliminar: ");
            String username = sc.nextLine();

            try{
                model.deleteUser(username);
                System.out.println("Usuario eliminado.");
                control = true;
                sc.nextLine();
            } catch (Exception ex){
                System.out.println("Ese usuario no existe.");
                sc.nextLine();
                control = true;
                break;
            }
        } while (!control);
    }

    private static void ClearConsole() {
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
