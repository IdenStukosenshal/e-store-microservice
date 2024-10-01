package ru.isands.test.estore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Controller
@RequestMapping("/")
public class HomeController {


    @GetMapping
    public String home() {
        return "home";
    }

    @PostMapping
    public String readFileZip(MultipartFile file, Model model) {
        if (!file.getOriginalFilename().endsWith(".zip")) {
            model.addAttribute("message", "Неверный формат файла");
        } else {
            try (ZipInputStream zin = new ZipInputStream(file.getInputStream())) {
                ZipEntry entry;
                String name;
                while ((entry = zin.getNextEntry()) != null) {
                    name = entry.getName(); // получим название файла
                    System.out.printf("File name: %s \n", name);
                    processingFile(zin, name);
                    zin.closeEntry();
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

    //Осталось только придумать как раскидать таблицы
    //Map с именами и мапперами  в отдельном классе?
    @Transactional//??? имеет ли смысл
    private void processingFile(InputStream is, String name) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

}