package ru.isands.test.estore.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.isands.test.estore.models.Shop;
import ru.isands.test.estore.repositories.ShopRepository;
import ru.isands.test.estore.services.PaginateService;

@Controller
public class ShopController {
    private final ShopRepository shopRepository;
    private final PaginateService<Shop, ShopRepository> paginateService;

    public ShopController(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
        this.paginateService = new PaginateService<>(shopRepository);
    }

    @GetMapping("/shops")
    public String shops(Model model,
                        @RequestParam("page") int page,
                        @RequestParam("size") int size) {
        Page<Shop> shopPgLst = paginateService.findAllPaginated(page, size);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", shopPgLst.getTotalPages());
        model.addAttribute("totalItems", shopPgLst.getTotalElements());
        model.addAttribute("shopLst", shopPgLst.getContent());
        return "catalogs/shops.html";
    }
}