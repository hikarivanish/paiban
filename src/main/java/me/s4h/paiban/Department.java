package me.s4h.paiban;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by hikari on 2015/6/2.
 */


@Entity
public class Department {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Department() {
    }

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

    public Department(String name) {
        this.name = name;
    }
}
