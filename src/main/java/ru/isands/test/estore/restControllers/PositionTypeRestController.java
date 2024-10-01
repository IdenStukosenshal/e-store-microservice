package ru.isands.test.estore.restControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.exceptions.EntityNotFOundException;
import ru.isands.test.estore.models.PositionType;
import ru.isands.test.estore.repositories.PositionTypeRepository;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Position Type", description = "Сервис для выполнения операций над таблицей должностей сотрудников")
@RestController
@RequestMapping("rest/position-types")
public class PositionTypeRestController {

    private final PositionTypeRepository positionTypeRepository;

    public PositionTypeRestController(PositionTypeRepository positionTypeRepository) {
        this.positionTypeRepository = positionTypeRepository;
    }


    @GetMapping("/")
    public List<PositionType> getAllPositionTypes() {
        return positionTypeRepository.findAll();
    }

    @GetMapping("/{id}")
    public PositionType getById(@PathVariable Long id) {
        return positionTypeRepository.findById(id).orElseThrow(EntityNotFOundException::new);
    }

    @PostMapping("/")
    public PositionType addNewPositionType(@Valid @RequestBody PositionType obj) {
        return positionTypeRepository.save(obj);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        if (positionTypeRepository.existsById(id)) {
            positionTypeRepository.deleteById(id);
        } else throw new EntityNotFOundException();
    }

    @DeleteMapping("/")
    public void deleteAllPositionTypes(){
        positionTypeRepository.deleteAll();
    }

    @PutMapping("/")
    public PositionType update(@Valid @RequestBody PositionType newObj){
        return positionTypeRepository
                .findById(newObj.getId())
                .map(oldObj->{
                    oldObj.setName(newObj.getName());
                    return positionTypeRepository.save(oldObj);
                })
                .orElseThrow(EntityNotFOundException::new);
    }
}
