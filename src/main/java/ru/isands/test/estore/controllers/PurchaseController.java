package ru.isands.test.estore.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.isands.test.estore.models.Purchase;
import ru.isands.test.estore.repositories.PurchaseRepository;
import ru.isands.test.estore.services.PaginateService;

@Controller
public class PurchaseController {
    private final PurchaseRepository purchaseRepository;
    private final PaginateService<Purchase, PurchaseRepository> paginateService;

    public PurchaseController(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
        this.paginateService = new PaginateService<>(purchaseRepository);
    }

    @GetMapping("/purchases")
    public String purchases(Model model,
                            @RequestParam("page") int page,
                            @RequestParam("size") int size) {
        Page<Purchase> purchasePgLst = paginateService.findAllPaginated(page, size);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", purchasePgLst.getTotalPages());
        model.addAttribute("totalItems", purchasePgLst.getTotalElements());
        model.addAttribute("purchaseLst", purchasePgLst.getContent());
        return "sections/purchases.html";
    }
}
