package ru.isands.test.estore.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.isands.test.estore.models.ElectroEmployee;
import ru.isands.test.estore.repositories.ElectroEmployeeRepository;
import ru.isands.test.estore.services.PaginateService;

@Controller
public class ElectroEmployeeController {
    private final ElectroEmployeeRepository electroEmployeeRepository;
    private final PaginateService<ElectroEmployee, ElectroEmployeeRepository> paginateService;

    public ElectroEmployeeController(ElectroEmployeeRepository electroEmployeeRepository) {
        this.electroEmployeeRepository = electroEmployeeRepository;
        this.paginateService = new PaginateService<>(electroEmployeeRepository);
    }

    @GetMapping("/electro-employee")
    public String electroEmployee(Model model,
                                  @RequestParam("page") int page,
                                  @RequestParam("size") int size) {
        Page<ElectroEmployee> electroEmployeePgLst = paginateService.findAllPaginated(page, size);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", electroEmployeePgLst.getTotalPages());
        model.addAttribute("totalItems", electroEmployeePgLst.getTotalElements());
        model.addAttribute("electroEmployeeLst", electroEmployeePgLst.getContent());
        return "connection-tables/electro-employee.html";
    }
}
