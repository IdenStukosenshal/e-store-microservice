package ru.isands.test.estore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.isands.test.estore.models.ElectroItem;
import ru.isands.test.estore.repositories.ElectroItemRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/electro-items")
public class ElectroItemController {
    private final ElectroItemRepository electroItemRepository;

    private final Function<String, ElectroItem> mapperElectroItemCSV = (line)->{
        String[] fields = line.split(";");
        if(fields.length != 7) {
            return null;
        }
        ElectroItem etm = new ElectroItem();
        etm.setId(Long.parseLong(fields[0]));
        etm.setName(fields[1]);
        etm.setETypeId(Long.parseLong(fields[2]));
        etm.setPrice(Long.parseLong(fields[3]));
        etm.setCount(Integer.parseInt(fields[4]));
        etm.setArchive(Boolean.parseBoolean(fields[5]));
        etm.setDescription(fields[6]);
        return etm;
    };

    public ElectroItemController(ElectroItemRepository electroItemRepository) {
        this.electroItemRepository = electroItemRepository;
    }

    @GetMapping
    public String electroItems(Model model) {
        List<ElectroItem> electroItemLst = electroItemRepository.findAll();
        model.addAttribute("electroItemLst", electroItemLst);
        return "sections/electro-items.html";
    }

    @PostMapping
    public String uploadElectroTypes(MultipartFile file,
                                     Model model) {

        if (!file.getOriginalFilename().endsWith(".csv")) {
            model.addAttribute("message", "Неверный формат файла");
        } else {
            List<ElectroItem> eltpL;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                eltpL = br.lines().skip(1).map(mapperElectroItemCSV).collect(Collectors.toList());
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

