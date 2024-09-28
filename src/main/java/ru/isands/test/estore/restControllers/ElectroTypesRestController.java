package ru.isands.test.estore.restControllers;

import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.exceptions.EntityNotFOundException;
import ru.isands.test.estore.models.ElectroType;
import ru.isands.test.estore.repositories.ElectroTypeRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("rest/electro-types")
public class ElectroTypesRestController {

    private final ElectroTypeRepository electroTypeRepository;

    public ElectroTypesRestController(ElectroTypeRepository electroTypeRepository) {
        this.electroTypeRepository = electroTypeRepository;
    }

    @GetMapping("/")
    public List<ElectroType> getAllElectroTypes() {
        return electroTypeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ElectroType getById(@PathVariable Long id) {
        return electroTypeRepository.findById(id).orElseThrow(EntityNotFOundException::new);
    }

    @PostMapping("/")
    public ElectroType addNewElectroType(@Valid @RequestBody ElectroType obj) {
        return electroTypeRepository.save(obj);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        if (electroTypeRepository.findById(id).isPresent()) {
            electroTypeRepository.deleteById(id);
        } else throw new EntityNotFOundException();
    }

    @DeleteMapping("/")
    public void deleteAllElectroTypes() {
        electroTypeRepository.deleteAll();
    }

    @PutMapping("/")
    public ElectroType update(@RequestBody ElectroType newObj) {
        return electroTypeRepository.
                findById(newObj.getId())
                .map(oldObj -> {
                    oldObj.setName(newObj.getName());
                    return electroTypeRepository.save(oldObj);
                })
                .orElseThrow(EntityNotFOundException::new);
    }
}
