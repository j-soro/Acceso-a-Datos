package com.jsc_ad.ejercicio_u3;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int project_id;
    private String title;
    private String description;
    private int deadline;

    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = null;

    public Project() {
    }

    public Project(String title, String description, int deadline) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public Set<Employee> getEmployees() {
        if (this.employees == null){
            this.employees = new HashSet<>();
        }
        return employees;
    }

    public void setEmployees(Employee ... args) {
            for (Employee e: args) {
                this.addEmployee(e);
            }
    }

    public void addEmployee(Employee e) {
        if (this.getEmployees() == null){
            this.employees = new HashSet<>();
        }
        this.employees.add(e);
        e.getProjects().add(this);
    }

    public void removeEmployee(Employee e) {
        if (this.getEmployees().contains(e)){
            this.employees.remove(e);
            e.getProjects().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + project_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", employees=" + employees +
                '}';
    }
}