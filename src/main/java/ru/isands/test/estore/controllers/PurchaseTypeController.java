package ru.isands.test.estore.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.isands.test.estore.models.PurchaseType;
import ru.isands.test.estore.repositories.PurchaseTypeRepository;
import ru.isands.test.estore.services.PaginateService;

@Controller
public class PurchaseTypeController {
    private final PurchaseTypeRepository purchaseTypeRepository;
    private final PaginateService<PurchaseType, PurchaseTypeRepository> paginateService;

    public PurchaseTypeController(PurchaseTypeRepository purchaseTypeRepository) {
        this.purchaseTypeRepository = purchaseTypeRepository;
        this.paginateService = new PaginateService<>(purchaseTypeRepository);
    }

    @GetMapping("/purchase-types")
    public String purchaseTypes(Model model,
                                @RequestParam("page") int page,
                                @RequestParam("size") int size) {
        Page<PurchaseType> PgLst = paginateService.findAllPaginated(page, size);
        paginateService.addAtributesToModel(page, size, PgLst, model);
        return "catalogs/purchase-types.html";
    }
}
