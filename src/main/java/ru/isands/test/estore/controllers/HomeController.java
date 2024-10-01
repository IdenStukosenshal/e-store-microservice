package ru.isands.test.estore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.isands.test.estore.enums.NamesAndOrders;
import ru.isands.test.estore.services.FileProcessingService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Controller
@RequestMapping("/")
public class HomeController {
    private final FileProcessingService fileProcessingService;
    public HomeController(FileProcessingService fileProcessingService) {
        this.fileProcessingService = fileProcessingService;
    }

    @GetMapping
    public String home() {
        return "home";
    }

    //Пока так себе
    @PostMapping
    public String readFileZip(MultipartFile file, Model model) {
        if (!file.getOriginalFilename().endsWith(".zip")) {
            model.addAttribute("message", "Неверный формат файла");
        } else {
            try (ZipInputStream zin = new ZipInputStream(file.getInputStream())) {
                ZipEntry entry;
                String name;
                Map<String, List<String>> allFilesMap = new HashMap<>();

                while ((entry = zin.getNextEntry()) != null) {
                    name = entry.getName();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(zin));
                    String line;
                    List<String> oneFileList = new ArrayList<>();
                    while ((line = reader.readLine()) != null) {
                        oneFileList.add(line);
                    }
                    allFilesMap.put(name, oneFileList);
                    zin.closeEntry();
                }
                for (NamesAndOrders nm : NamesAndOrders.values()) {
                    if (allFilesMap.containsKey(nm.getText())) {
                        fileProcessingService.processingFile(allFilesMap.get(nm.getText()), nm);
                    } else {
                        System.out.println("Ошибка чтения файла: " + nm);
                        throw new IOException();//если имя не совпало
                    }
                }
            } catch (IOException ex) {
                model.addAttribute("message", "ошибка чтения файла");
                return "home";
            }
        }
        model.addAttribute("message", "Файл "
                + file.getOriginalFilename() + " был загружен");
        return "home";
    }


}