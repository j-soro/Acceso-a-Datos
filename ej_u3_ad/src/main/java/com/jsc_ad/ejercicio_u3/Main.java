package com.jsc_ad.ejercicio_u3;

import com.jsc_ad.ejercicio_u3.model.EmployeeModel;
import org.hibernate.Transaction;

public class Main {

    public static void main (String[] args) {

        Transaction t = EmployeeModel.getSession().beginTransaction();

        Project x1 = new Project("Bullseye VII: The Reckoning", "Videojuego PC, Java y C++", 2024);
        Project x2 = new Project("VuelaMás Phone ", "Aplicación de gestión de vuelos, Kotlin", 2022);
        Project x3 = new Project("Markie Markdown Editor", "Editor de textos Markdown, Windows, C#", 2030);
        Project x4 = new Project("Bejeweeled LXVIII", "Juego para Android, Kotlin", 2024);
        Project x5 = new Project("GuitarMaster 4", "Aplicación para tablaturas de guitarra, C#", 2026);

        Employee e1 = new Employee("Joaquín", "Soro Castelló", 1500);
        Employee e2 = new Employee("Javier", "García Sempere", 1500);
        Employee e3 = new Employee("Pablo", "Alborán Cantante", 1500);
        Employee e4 = new Employee("Blas", "Abad JuanJesús", 1500);
        Employee e5 = new Employee("Pepe", "Figuera Martínez", 1500);
        Employee e6 = new Employee("Enrique", "Folgado Doblado", 1500);
        Employee e7 = new Employee("Jaime", "Pérez Hernández", 1500);
        Employee e8 = new Employee("Antonio", "Maestre Sánchez", 1500);

        e1.setProjects(x2, x1, x3, x4);
        e2.addProject(x1);
        e3.setProjects(x2, x3, x4);
        e4.setProjects(x4, x1);
        e5.setProjects(x2);
        e6.setProjects(x3, x1);
        e7.setProjects(x4);

        EmployeeModel.getSession().save(e1);
        EmployeeModel.getSession().save(e2);
        EmployeeModel.getSession().save(e3);
        EmployeeModel.getSession().save(e4);
        EmployeeModel.getSession().save(e5);
        EmployeeModel.getSession().save(e6);
        EmployeeModel.getSession().save(e7);
        EmployeeModel.getSession().save(e8);

        e8.addProject(x5);

        t.commit();

    }
}
