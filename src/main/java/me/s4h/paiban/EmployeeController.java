package me.s4h.paiban;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hikari on 2015/6/2.
 */

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        employeeRepository.delete(id);
        return "redirect:/employee";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable Long id, String name, Long departmentId) {
        Employee e = employeeRepository.findOne(id);
        if (e != null) {
            e.setName(name);
            e.setDepartment(departmentRepository.findOne(departmentId));
            employeeRepository.save(e);
        }
        return "redirect:/employee";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Employee e = employeeRepository.findOne(id);
//        model.addAttribute("id",e.getId());
        model.addAttribute("name", e.getName());
        model.addAttribute("departmentId", e.getDepartment().getId());
        model.addAttribute("allDepartment", departmentRepository.findAll());
        return "employeeEdit";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Employee employee, Model model) {
        model.addAttribute("allDepartment", departmentRepository.findAll());
        return "employee";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(String name, Long departmentId) {
        System.out.println(departmentId);
        employeeRepository.save(new Employee(name, departmentRepository.findOne(departmentId)));
        return "redirect:/employee";
    }


    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model, Pageable pageable) {
        model.addAttribute("employees", employeeRepository.findAll(pageable));
        model.addAttribute("page",pageable.getPageNumber());
        return "employeeList";
    }

/*    @RequestMapping(value = "/list/{pageStart}/{numPerPage}", method = RequestMethod.GET)
    public String list(Model model, @PathVariable Integer pageStart, @PathVariable Integer numPerPage) {
        Page<Employee> page = employeeRepository.findAll()

        return "employeeList";
    }*/

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "hello";
    }
}
