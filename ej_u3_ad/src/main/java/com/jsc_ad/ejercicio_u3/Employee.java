package com.jsc_ad.ejercicio_u3;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    private String firstName;
    private String lastName;
    private float salary;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "employee_project",
            joinColumns = { @JoinColumn(name = "employee_id")},
            inverseJoinColumns = { @JoinColumn(name = "project_id")}
    )
    private Set<Project> projects = null;

    public Employee() {
    }

    public Employee(String firstName, String lastName, float salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Set<Project> getProjects() {
        if (this.projects == null){
            this.projects = new HashSet<>();
        }
        return projects;
    }

    public void setProjects(Project ... args) {
            for (Project p: args) {
                this.addProject(p);
            }
    }

    public void addProject(Project p) {
        if (this.getProjects() == null){
            this.projects = new HashSet<>();
        }
        this.projects.add(p);
        p.getEmployees().add(this);
    }

    public void removeProject(Project p) {
        if (this.getProjects().contains(p)){
            this.projects.remove(p);
            p.getEmployees().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + employee_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", projects=" + projects +
                '}';
    }

    public Object[] toArray() {
        return new Object[]{
                this.getEmployee_id(),
                this.getFirstName(),
                this.getLastName(),
                this.getSalary(),
                this.getProjects()
        };
    }
}
