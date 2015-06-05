package me.s4h.paiban;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class PaibanApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaibanApplication.class, args);
    }
}


@Component
class CmdRunner implements CommandLineRunner {
    private static final Random random = new Random();


    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        departmentRepository.save(Arrays.asList(
                new Department("taobao"),
                new Department("zhifubao"),
                new Department("alibaba"),
                new Department("xiami"),
                new Department("cainiao")
        ));

        List<Department> ds = departmentRepository.findAll();
        for (int i = 1; i < 100; i++) {
            employeeRepository.save(new Employee("empl" + random.nextFloat() * 100, true, "fdsf" + random.nextFloat() + "@fds.com", random.nextInt(4) + 1L));
        }


    }
}