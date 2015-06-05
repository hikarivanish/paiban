package me.s4h.paiban;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hikari on 2015/6/4.
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Department> listAll() {
        return departmentRepository.findAll();
    }

}
