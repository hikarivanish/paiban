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

    private Boolean inSchedule;
    private String email;

    private Long departmentId;


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

    public Boolean getInSchedule() {
        return inSchedule;
    }

    public void setInSchedule(Boolean inSchedule) {
        this.inSchedule = inSchedule;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isInSchedule=" + inSchedule +
                ", email='" + email + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }

    public Employee(String name, Boolean isInSchedule, String email, Long departmentId) {
        this.name = name;
        this.inSchedule = isInSchedule;
        this.email = email;
        this.departmentId = departmentId;
    }

    public Employee() {
    }
}
