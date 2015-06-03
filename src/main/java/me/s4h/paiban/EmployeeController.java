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
        System.out.println(id + "   " + name + "  " + departmentId);
        Employee e = employeeRepository.findOne(id);
        if (e != null) {
            e.setName(name);
            e.setDepartment(departmentRepository.findOne(departmentId));
            employeeRepository.save(e);
        }
        return "redirect:/employee";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(@RequestBody Employee empl) {
        System.out.println(empl);
        Employee e = employeeRepository.findOne(empl.getId());
        if (e != null) {
            e.setName(empl.getName());
            e.setDepartment(departmentRepository.findOne(empl.getDepartment().getId()));
            employeeRepository.save(e);
        }
        return "{\"ok\":true}";
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


    /*    @RequestMapping(value = "/add", method = RequestMethod.POST)
        public String add(String name, Long departmentId) {
            System.out.println(departmentId);
            employeeRepository.save(new Employee(name, departmentRepository.findOne(departmentId)));
            return "redirect:/employee";
        }*/
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody Employee empl) {
        System.out.println(empl);
        empl.setDepartment(departmentRepository.findOne(empl.getDepartment().getId()));
        employeeRepository.save(empl);
        return "{\"ok\":true}";
    }

    /*    @RequestMapping(method = RequestMethod.GET)
        public String home(Model model, Pageable pageable) {
            model.addAttribute("employees", employeeRepository.findAll(pageable));
            model.addAttribute("page",pageable.getPageNumber());
            return "employeeList";
        }   */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page<Employee> home(Model model, Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @RequestMapping("/departments")
    @ResponseBody
    public List<Department> departments() {
        return departmentRepository.findAll();
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
