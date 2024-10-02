package ru.isands.test.estore.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.isands.test.estore.models.ElectroType;
import ru.isands.test.estore.repositories.ElectroTypeRepository;
import ru.isands.test.estore.services.MapperSevice;
import ru.isands.test.estore.services.PaginateService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/electro-types")
public class ElectroTypesController {

    private final ElectroTypeRepository electroTypeRepository;
    private final MapperSevice mapperSevice;
    private final PaginateService<ElectroType, ElectroTypeRepository> paginateService;

    public ElectroTypesController(ElectroTypeRepository electroTypeRepository,
                                  MapperSevice mapperSevice
    ) {
        this.electroTypeRepository = electroTypeRepository;
        this.mapperSevice = mapperSevice;
        this.paginateService = new PaginateService<>(electroTypeRepository);
    }

    @GetMapping
    public String electro_types(Model model,
                                @RequestParam("page") int page,
                                @RequestParam("size") int size) {
        Page<ElectroType> PgLst = paginateService.findAllPaginated(page, size);
        paginateService.addAtributesToModel(page, size, PgLst, model);
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
