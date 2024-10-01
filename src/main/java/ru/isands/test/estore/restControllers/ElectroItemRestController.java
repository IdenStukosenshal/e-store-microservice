package ru.isands.test.estore.restControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.exceptions.EntityNotFOundException;

import ru.isands.test.estore.models.ElectroItem;
import ru.isands.test.estore.repositories.ElectroItemRepository;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Electro Items", description = "Сервис для выполнения операций над таблицей электротоваров")
@RestController
@RequestMapping("rest/electro-items")
public class ElectroItemRestController {

    private final ElectroItemRepository electroItemRepository;

    public ElectroItemRestController(ElectroItemRepository electroItemRepository) {
        this.electroItemRepository = electroItemRepository;
    }

    @GetMapping("/")
    public List<ElectroItem> getAllElectroTypes() {
        return electroItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ElectroItem getById(@PathVariable Long id) {
        return electroItemRepository.findById(id).orElseThrow(EntityNotFOundException::new);
    }

    @PostMapping("/")
    public ElectroItem addNewElectroType(@Valid @RequestBody ElectroItem obj) {
        return electroItemRepository.save(obj);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        if (electroItemRepository.existsById(id)) {
            electroItemRepository.deleteById(id);
        } else throw new EntityNotFOundException();
    }

    @DeleteMapping("/")
    public void deleteAllElectroTypes() {
        electroItemRepository.deleteAll();
    }

    @PutMapping("/")
    public ElectroItem update(@Valid @RequestBody ElectroItem newObj) {
        return electroItemRepository.
                findById(newObj.getId())
                .map(oldObj -> {
                    oldObj.setName(newObj.getName());
                    oldObj.setETypeId(newObj.getETypeId());
                    oldObj.setPrice(newObj.getPrice());
                    oldObj.setCount(newObj.getCount());
                    oldObj.setArchive(newObj.getArchive());
                    oldObj.setDescription(newObj.getDescription());
                    return electroItemRepository.save(oldObj);
                })
                .orElseThrow(EntityNotFOundException::new);
    }
}
