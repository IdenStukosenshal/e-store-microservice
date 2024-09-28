package ru.isands.test.estore.restControllers;

import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.exceptions.EntityNotFOundException;
import ru.isands.test.estore.models.ElectroType;
import ru.isands.test.estore.repositories.ElectroTypeRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ElectroTypesRestController {

    private final ElectroTypeRepository electroTypeRepository;

    public ElectroTypesRestController(ElectroTypeRepository electroTypeRepository) {
        this.electroTypeRepository = electroTypeRepository;
    }

    @GetMapping("/rest/electro_types")
    public List<ElectroType> getAllElectroTypes() {
        return electroTypeRepository.findAll();
    }

    @GetMapping("/rest/electro_types/{id}")
    public ElectroType getById(@PathVariable Long id) {
        return electroTypeRepository.findById(id).orElseThrow(EntityNotFOundException::new);
    }

    @PostMapping("/rest/electro_types")
    public ElectroType addNewElectroType(@Valid @RequestBody ElectroType obj) {
        return electroTypeRepository.save(obj);
    }

    @DeleteMapping("/rest/electro_types/{id}")
    public void deleteById(@PathVariable Long id) {
        if (electroTypeRepository.findById(id).isPresent()) {
            electroTypeRepository.deleteById(id);
        } else throw new EntityNotFOundException();
    }

    @DeleteMapping("/rest/electro_types")
    public void deleteAllElectroTypes() {
        electroTypeRepository.deleteAll();
    }

    @PutMapping("/rest/electro_types/{id}")
    public ElectroType updateOrCreate(@RequestBody ElectroType newObj, @PathVariable Long id) {
        return electroTypeRepository.
                findById(id)
                .map(oldObj -> {
                    oldObj.setName(newObj.getName());
                    return electroTypeRepository.save(oldObj);
                })
                .orElseGet(() -> {
                    newObj.setId(id);
                    return electroTypeRepository.save(newObj);
                });
    }
}
