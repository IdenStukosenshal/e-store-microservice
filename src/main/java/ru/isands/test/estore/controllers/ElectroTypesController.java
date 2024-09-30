package ru.isands.test.estore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.isands.test.estore.models.ElectroType;
import ru.isands.test.estore.repositories.ElectroTypeRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/electro-types")
public class ElectroTypesController {

    private final ElectroTypeRepository electroTypeRepository;

    private final Function<String, ElectroType> mapperElectroTypeCSV = (line) -> {
        String[] fields = line.split(";");
        if (fields.length != 2) {
            //2 - количество полей модели
            return null;
        }
        ElectroType etp = new ElectroType();
        etp.setId(Long.parseLong(fields[0]));
        etp.setName(fields[1]);
        return etp;
    };

    public ElectroTypesController(ElectroTypeRepository electroTypeRepository) {
        this.electroTypeRepository = electroTypeRepository;
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

        if (!file.getOriginalFilename().endsWith(".csv")) {
            model.addAttribute("message", "Неверный формат файла");
        } else {
            List<ElectroType> eltpL;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                eltpL = br.lines().skip(1).map(mapperElectroTypeCSV).collect(Collectors.toList());
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
