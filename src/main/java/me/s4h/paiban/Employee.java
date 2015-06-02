package me.s4h.paiban;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by hikari on 2015/6/2.
 */


@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;


    private String name;

    @ManyToOne
    private Department department;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public Employee() {
    }
}
