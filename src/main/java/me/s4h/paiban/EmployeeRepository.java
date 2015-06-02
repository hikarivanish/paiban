package me.s4h.paiban;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hikari on 2015/6/2.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
