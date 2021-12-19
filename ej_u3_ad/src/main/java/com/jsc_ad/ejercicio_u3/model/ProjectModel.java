package com.jsc_ad.ejercicio_u3.model;

import com.jsc_ad.ejercicio_u3.Employee;
import com.jsc_ad.ejercicio_u3.HibernateUtil;
import com.jsc_ad.ejercicio_u3.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class ProjectModel {

    private static Set<Project> projectSet = new HashSet<>();
    private static Session session = null;

    // INICIALIZADOR ESTÁTICO
    static{
        if(session == null){
            startSession();
        }
    }

    public static void startSession(){
        session = HibernateUtil.getSession();
    }

    public static void closeSession(){
        HibernateUtil.closeSession();
    }

    public static Set<Project> getProjectSet() {
        return projectSet;
    }

    public static void setProjectSet(Set<Project> projectSet) {
        ProjectModel.projectSet = projectSet;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        ProjectModel.session = session;
    }

    // MÉTODOS DE C.R.U.D. EN LA BASE DE DATOS
    public static void saveProject(Project p) {
        Transaction t = session.beginTransaction();
        session.save(p);
        t.commit();
    }

    public static Project findProjectById(int id) {

        Query<Project> q = session.createQuery("from Project where project_id = :x", Project.class);
        q.setParameter("x", id);
        return q.getSingleResult();
    }

    public static void updateProject(Project p, Project newer ) {

        p.setTitle(newer.getTitle());
        p.setDescription(newer.getDescription());
        p.setDeadline(newer.getDeadline());
        p.setEmployees((Employee) newer.getEmployees());

        Transaction t = session.beginTransaction();
        session.update(p);
        t.commit();
    }

    public static void deleteProject(Project p) {

        Transaction t = session.beginTransaction();
        session.remove(p);
        t.commit();
    }

    public static void getAllProjects() {

        Query<Project> q = session.createQuery("from Project order by project_id asc");
        Iterator<Project> it = q.iterate();
        while (it.hasNext()){
            projectSet.add(it.next());
        }
    }

    public static Object[][] getTableData() {

        getAllProjects();
        Project[] data_array = projectSet.toArray(new Project[projectSet.size()]);
        Object[][] data = new Object[data_array.length][6];

        for (int i = 0; i < data_array.length; i++){
            for (int j = 0; j < 6; j++){

                switch (j){

                    case 0:
                        data[i][j] = data_array[i].getProject_id();
                        break;
                    case 1:
                        data[i][j] = data_array[i].getTitle();
                        break;
                    case 2:
                        data[i][j] = data_array[i].getDescription();
                        break;
                    case 3:
                        data[i][j] = data_array[i].getDeadline();
                        break;
                    case 4:
                        data[i][j] = data_array[i].getEmployees().size();
                        break;
                }
            }
        }
        return data;
    }
}
