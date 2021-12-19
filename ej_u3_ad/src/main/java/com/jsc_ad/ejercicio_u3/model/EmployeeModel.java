package com.jsc_ad.ejercicio_u3.model;

import com.jsc_ad.ejercicio_u3.Employee;
import com.jsc_ad.ejercicio_u3.HibernateUtil;
import com.jsc_ad.ejercicio_u3.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class EmployeeModel {

    private static Set<Employee> employeeSet = new HashSet<>();
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

    public static Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public static void setEmployeeSet(Set<Employee> employeeSet) {
        EmployeeModel.employeeSet = employeeSet;
    }

    public static Session getSession() {
        return session;
    }

    // MÉTODOS DE C.R.U.D. EN LA BASE DE DATOS
    // Crear
    public static void saveEmp(Employee e) {
        Transaction t = session.beginTransaction();
        session.save(e);
        t.commit();
    }
    // Buscar
    public static Employee findEmpById(int id) {

        Query<Employee> q = session.createQuery("from Employee where employee_id = :x", Employee.class);
        q.setParameter("x", id);
        return q.getSingleResult();
    }
    // Editar
    public static void updateEmp(Employee e, Employee newer ) {

        e.setFirstName(newer.getFirstName());
        e.setLastName(newer.getLastName());
        e.setSalary(newer.getSalary());
        e.setProjects((Project) newer.getProjects());

        Transaction t = session.beginTransaction();
        session.update(e);
        t.commit();
    }
    // Borrar
    public static void deleteEmp(Employee e) {
        employeeSet.remove(e);
        Transaction t = session.beginTransaction();
        session.remove(e);
        t.commit();
    }

    public static void getAllEmployees() {

        Query<Employee> q = session.createQuery("from Employee order by employee_id asc");
        Iterator<Employee> it = q.iterate();
        while (it.hasNext()){
            employeeSet.add(it.next());
        }
    }

    public static Object[][] getTableData() {

        getAllEmployees();
        Employee[] data_array = employeeSet.toArray(new Employee[employeeSet.size()]);
        Object[][] data = new Object[data_array.length][6];

        for (int i = 0; i < data_array.length; i++){
            for (int j = 0; j < 6; j++){

                switch (j){

                    case 0:
                        data[i][j] = data_array[i].getEmployee_id();
                        break;
                    case 1:
                        data[i][j] = data_array[i].getFirstName();
                        break;
                    case 2:
                        data[i][j] = data_array[i].getLastName();
                        break;
                    case 3:
                        data[i][j] = data_array[i].getSalary();
                        break;
                    case 4:
                        data[i][j] = data_array[i].getProjects().size();
                        break;
                }
            }
        }
        return data;
    }
}
