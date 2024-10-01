package ru.isands.test.estore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.isands.test.estore.models.ElectroType;
import ru.isands.test.estore.repositories.ElectroTypeRepository;
import ru.isands.test.estore.services.MapperSevice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/electro-types")
public class ElectroTypesController {

    private final ElectroTypeRepository electroTypeRepository;
    private final MapperSevice mapperSevice;


    public ElectroTypesController(ElectroTypeRepository electroTypeRepository,
                                  MapperSevice mapperSevice) {
        this.electroTypeRepository = electroTypeRepository;
        this.mapperSevice = mapperSevice;
    }


    @GetMapping
    public String electro_types(Model model) {
        List<ElectroType> electroTypeList = electroTypeRepository.findAll();
        model.addAttribute("electroTypeList", electroTypeList);
        return "catalogs/electro-types.html";
    }

    @PostMapping
    public String uploadElectroTypes(MultipartFile file,
                                     Model model) {
        String fileName = file.getOriginalFilename();

        if (!fileName.endsWith(".csv")) {
            model.addAttribute("message", "Неверный формат файла");
        } else {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                var eltpL = br.lines().skip(1).map(mapperSevice::mapperElectroTypeCSV).collect(Collectors.toList());
                electroTypeRepository.saveAll(eltpL);
            } catch (IOException ee) {
                model.addAttribute("message", "ошибка чтения файла");
                return "catalogs/electro-types.html";
            }
        }
        model.addAttribute("message", "Файл "
                + file.getOriginalFilename() + " был загружен");
        return "redirect:/electro-types";
    }
}
