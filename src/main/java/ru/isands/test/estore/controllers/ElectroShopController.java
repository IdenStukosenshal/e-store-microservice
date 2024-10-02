package ru.isands.test.estore.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.isands.test.estore.models.ElectroShop;
import ru.isands.test.estore.repositories.ElectroShopRepository;
import ru.isands.test.estore.services.PaginateService;

@Controller
public class ElectroShopController {
    private final ElectroShopRepository electroShopRepository;
    private final PaginateService<ElectroShop, ElectroShopRepository> paginateService;

    public ElectroShopController(ElectroShopRepository electroShopRepository) {
        this.electroShopRepository = electroShopRepository;
        this.paginateService = new PaginateService<>(electroShopRepository);
    }

    @GetMapping("/electro-shop")
    public String electroShop(Model model,
                              @RequestParam("page") int page,
                              @RequestParam("size") int size) {
        Page<ElectroShop> electroShopPgLst = paginateService.findAllPaginated(page, size);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", electroShopPgLst.getTotalPages());
        model.addAttribute("totalItems", electroShopPgLst.getTotalElements());
        model.addAttribute("electroShopLst", electroShopPgLst.getContent());

        return "connection-tables/electro-shop.html";
    }
}
