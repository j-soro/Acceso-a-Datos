package com.dam2;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class Main {
    private static EntityManager em;

    public static void main(String[] args) {

        // Guardar una entidad y su entidad embebida
        em = Connection.getManager();

        System.out.println("Guardando información en BD...\n");

        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();

            User user1 = new User();
            user1.setUsername("jsoro");
            user1.setPassword("1234");
            user1.setFirstname("Joaquín");
            user1.setLastname("Soro");

            Session s1 = new Session();
            s1.setSessionDate("02-02-2022");
            s1.setSessionName("Sesión de hoy");
            user1.setUserSession(s1);

            em.persist(user1);
            et.commit();
        } catch (Exception ex) {
            et.rollback();
            ex.printStackTrace();
        }
        System.out.println("Completado.\n");


        System.out.println("Leyendo información de la BD...\n");

        // Leer de la base de datos dicha entidad
        try {
            TypedQuery<User> q = em.createQuery("SELECT u FROM User u", User.class);

            List<User> result = q.getResultList();

            for (User u : result) {
                System.out.println("*** Usuario ***\n");
                System.out.println("ID: "+u.getId());
                System.out.println("Nick: "+u.getUsername());
                System.out.println("Nombre: "+u.getFirstname());
                System.out.println("Apellido: "+u.getLastname());
                System.out.println("Sesión: \n");
                System.out.println("\tID: "+u.getUserSession().getId());
                System.out.println("\tNombre: "+u.getUserSession().getSessionName());
                System.out.println("\tFecha: "+u.getUserSession().getSessionDate());
                System.out.println("*** JSON ***");
                System.out.println(u);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Completado.");
        }

        System.out.println("Saliendo del programa...");
        em.close();
    }
}
