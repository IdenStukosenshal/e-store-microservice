package ru.isands.test.estore.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.isands.test.estore.models.PositionType;
import ru.isands.test.estore.repositories.PositionTypeRepository;
import ru.isands.test.estore.services.PaginateService;

@Controller
public class PositionTypeController {
    private final PositionTypeRepository positionTypeRepository;
    private final PaginateService<PositionType, PositionTypeRepository> paginateService;

    public PositionTypeController(PositionTypeRepository positionTypeRepository) {
        this.positionTypeRepository = positionTypeRepository;
        this.paginateService = new PaginateService<>(positionTypeRepository);
    }

    @GetMapping("/position-types")
    public String positions(Model model,
                            @RequestParam("page") int page,
                            @RequestParam("size") int size) {
        Page<PositionType> positionsPgLst = paginateService.findAllPaginated(page, size);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", positionsPgLst.getTotalPages());
        model.addAttribute("totalItems", positionsPgLst.getTotalElements());
        model.addAttribute("positionsLst", positionsPgLst.getContent());
        return "catalogs/position-types.html";
    }
}
