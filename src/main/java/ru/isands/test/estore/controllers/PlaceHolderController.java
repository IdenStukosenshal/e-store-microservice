package ru.isands.test.estore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PlaceHolderController {

    @GetMapping("/employees")
    public String employees() {
        return "sections/employees.html";
    }

    @GetMapping("/purchases")
    public String purchases() {
        return "sections/purchases.html";
    }

    @GetMapping("/position-types")
    public String positions() {
        return "catalogs/position-types.html";
    }

    @GetMapping("/shops")
    public String shops() {
        return "catalogs/shops.html";
    }

    @GetMapping("/purchase-types")
    public String purchaseTypes() {
        return "catalogs/purchase-types.html";
    }

    @GetMapping("/electro-shop")
    public String electroShop() {
        return "Placeholder's Placeholder";
    }

    @GetMapping("/electro-employee")
    public String electroEmployee() {
        return "Placeholder's Placeholder";
    }


}
