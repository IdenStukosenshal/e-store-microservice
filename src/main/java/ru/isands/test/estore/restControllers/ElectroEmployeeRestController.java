package ru.isands.test.estore.restControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.exceptions.EntityNotFOundException;
import ru.isands.test.estore.models.ElectroEmployee;
import ru.isands.test.estore.models.ElectroEmployeePK;
import ru.isands.test.estore.repositories.ElectroEmployeeRepository;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Electro Employee", description = "Сервис для выполнения операций над таблицей связью электротовар-сотрудник")
@RestController
@RequestMapping("rest/electro-employee")
public class ElectroEmployeeRestController {

    private final ElectroEmployeeRepository electroEmployeeRepository;

    public ElectroEmployeeRestController(ElectroEmployeeRepository electroEmployeeRepository) {
        this.electroEmployeeRepository = electroEmployeeRepository;
    }


    @GetMapping("/")
    public List<ElectroEmployee> getAllElectroEmployee(){
        return electroEmployeeRepository.findAll();
    }

    @GetMapping("/{pk1}/{pk2}")
    public ElectroEmployee getById(@PathVariable Long pk1, @PathVariable Long pk2){
        ElectroEmployeePK eePK = new ElectroEmployeePK(pk1, pk2);
        return electroEmployeeRepository.findById(eePK).orElseThrow(EntityNotFOundException::new);
    }


    @PostMapping("/")
    public ElectroEmployee addNewElectroEmployee(@Valid @RequestBody ElectroEmployee obj){
        return electroEmployeeRepository.save(obj);
    }

    @DeleteMapping("/{pk1}/{pk2}")
    public void deleteById(@PathVariable Long pk1, @PathVariable Long pk2){
        ElectroEmployeePK eePK = new ElectroEmployeePK(pk1, pk2);
        if(electroEmployeeRepository.existsById(eePK)){
            electroEmployeeRepository.deleteById(eePK);
        } else throw new EntityNotFOundException();
    }

    @DeleteMapping("/")
    public void deleteAllElectroEmployee(){
        electroEmployeeRepository.deleteAll();
    }
}
