package ru.isands.test.estore.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.isands.test.estore.models.Employee;
import ru.isands.test.estore.repositories.EmployeeRepository;
import ru.isands.test.estore.services.PaginateService;

@Controller
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final PaginateService<Employee, EmployeeRepository> paginateService;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.paginateService = new PaginateService<>(employeeRepository);
    }

    @GetMapping("/employees")
    public String employees(Model model,
                            @RequestParam("page") int page,
                            @RequestParam("size") int size) {
        Page<Employee> PgLst = paginateService.findAllPaginated(page, size);
        paginateService.addAtributesToModel(page, size, PgLst, model);
        return "sections/employees.html";
    }
}
