package me.s4h.paiban;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hikari on 2015/6/2.
 */

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
    public String batchAdd(@RequestBody List<Employee> empls) {
        employeeRepository.save(empls);
        return "{\"ok\":true}";
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        employeeRepository.delete(id);
        return "{\"ok\":true}";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestBody Employee empl) {
        System.out.println(empl);
        employeeRepository.save(empl);
        return "{\"ok\":true}";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Employee empl) {
        System.out.println(empl);
        employeeRepository.save(empl);
        return "{\"ok\":true}";
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Employee> list(Model model, Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

}
