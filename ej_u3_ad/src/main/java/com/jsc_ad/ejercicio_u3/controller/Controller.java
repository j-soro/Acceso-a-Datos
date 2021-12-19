package com.jsc_ad.ejercicio_u3.controller;

import com.jsc_ad.ejercicio_u3.Employee;
import com.jsc_ad.ejercicio_u3.model.EmployeeModel;
import com.jsc_ad.ejercicio_u3.model.ProjectModel;

public class Controller {
    public static void saveEmployee(Employee e) {
        EmployeeModel.saveEmp(e);

    }

    public static void deleteEmployee(Employee e) {
        EmployeeModel.deleteEmp(e);
    }

    public static Object[][] getEmployeeData() {
        return EmployeeModel.getTableData();
    }

    public static Object[][] getProjectData() {
        return ProjectModel.getTableData();
    }

    public static Employee findEmployeeById(int id){
        return EmployeeModel.findEmpById(id);
    }
}
