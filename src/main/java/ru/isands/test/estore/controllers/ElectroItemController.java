package ru.isands.test.estore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.isands.test.estore.models.ElectroItem;
import ru.isands.test.estore.repositories.ElectroItemRepository;
import ru.isands.test.estore.services.MapperSevice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/electro-items")
public class ElectroItemController {
    private final ElectroItemRepository electroItemRepository;

    private final MapperSevice mapperSevice;

    public ElectroItemController(ElectroItemRepository electroItemRepository,
                                 MapperSevice mapperSevice) {
        this.electroItemRepository = electroItemRepository;
        this.mapperSevice = mapperSevice;
    }

    @GetMapping
    public String electroItems(Model model) {
        List<ElectroItem> electroItemLst = electroItemRepository.findAll();
        model.addAttribute("electroItemLst", electroItemLst);
        return "sections/electro-items.html";
    }

    @PostMapping
    public String uploadElectroItems(MultipartFile file,
                                     Model model) {
        String fileName = file.getOriginalFilename();

        if (!fileName.endsWith(".csv")) {
            model.addAttribute("message", "Неверный формат файла");
        } else {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                var eltpL = br.lines().skip(1).map(mapperSevice::mapperElectroItemCSV).collect(Collectors.toList());
                electroItemRepository.saveAll(eltpL);
            } catch (IOException ee) {
                model.addAttribute("message", "ошибка чтения файла");
                return "sections/electro-items.html";
            }
        }
        model.addAttribute("message", "Файл "
                + file.getOriginalFilename() + " был загружен");
        return "redirect:/electro-items";
    }
}

