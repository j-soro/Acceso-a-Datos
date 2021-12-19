package com.jsc_ad.ejercicio_u3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sf = null;
    private static Session globalSession = null;

    private HibernateUtil() {}
    public static SessionFactory getSF()
    {
        if (sf == null){

            Properties props = readProperties();
            Configuration conf = new Configuration().addProperties(props);
            conf.addAnnotatedClass(Employee.class);
            conf.addAnnotatedClass(Project.class);
            sf = conf.buildSessionFactory();
        }
        return sf;
    }

    public static Session getSession()
    {
        if (globalSession == null){

            SessionFactory sf = HibernateUtil.getSF();
            globalSession = sf.openSession();
        }
        return globalSession;
    }

    public static void closeSession(){
        globalSession.close();
    }

    public static Properties readProperties(){
        Properties props_read = new Properties();

        try {
            props_read.load(new FileReader("hibernate.properties"));

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return props_read;
    }
}
