package com.dam2;


import javax.persistence.*;

public class Connection {
    private static EntityManager em = null;

    private Connection() {

    }

    public static EntityManager getManager() {
        if (em == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/users.odb");
            em = emf.createEntityManager();
        }
        return em;
    }
}
