package ru.isands.test.estore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TEST_CONTRLS {

    @GetMapping("/employees")
    public String employees() {
        return "sections/employees.html";
    }

    @GetMapping("/purchases")
    public String purchases() {
        return "sections/purchases.html";
    }

    @GetMapping("/electro_items")
    public String electro_items() {
        return "sections/electro_items.html";
    }

    @GetMapping("/position_types")
    public String positions() {
        return "catalogs/position_types.html";
    }


    @GetMapping("/shops")
    public String shops() {
        return "catalogs/shops.html";
    }

    @GetMapping("/purchase_types")
    public String purchase_types() {
        return "catalogs/purchase_types.html";
    }


}
