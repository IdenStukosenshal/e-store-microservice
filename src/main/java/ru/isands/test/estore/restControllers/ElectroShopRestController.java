package ru.isands.test.estore.restControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.exceptions.EntityNotFOundException;
import ru.isands.test.estore.models.ElectroShop;
import ru.isands.test.estore.models.ElectroShopPK;
import ru.isands.test.estore.repositories.ElectroShopRepository;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Electro Shop", description = "Сервис для выполнения операций над таблицей связью магазин-товар  ")
@RestController
@RequestMapping("rest/electro-shop")
public class ElectroShopRestController {

    private final ElectroShopRepository electroShopRepository;

    public ElectroShopRestController(ElectroShopRepository electroShopRepository) {
        this.electroShopRepository = electroShopRepository;
    }


    @GetMapping("/")
    public List<ElectroShop> getAllElectroShops(){
        return electroShopRepository.findAll();
    }


    @GetMapping("/{pk1}/{pk2}")
    public ElectroShop getById(@PathVariable Long pk1, @PathVariable Long pk2){
        ElectroShopPK esPK = new ElectroShopPK(pk1, pk2);
        return electroShopRepository.findById(esPK).orElseThrow(EntityNotFOundException::new);
    }

    @PostMapping("/")
    public ElectroShop addNewElectroShop(@Valid @RequestBody ElectroShop obj){
        return electroShopRepository.save(obj);
    }

    @DeleteMapping("/{pk1}/{pk2}")
    public void deleteById(@PathVariable Long pk1, @PathVariable Long pk2){
        ElectroShopPK esPK = new ElectroShopPK(pk1, pk2);
        if(electroShopRepository.existsById(esPK)){
            electroShopRepository.deleteById(esPK);
        } else throw new EntityNotFOundException();
    }
    @DeleteMapping("/")
    public void deleteAllElectroShop(){
        electroShopRepository.deleteAll();
    }

    @PutMapping("/")
    public ElectroShop update(@Valid @RequestBody ElectroShop newObj){
        ElectroShopPK esPK = new ElectroShopPK(newObj.getShopId(), newObj.getElectroItemId());
        return electroShopRepository
                .findById(esPK)
                .map(oldObj -> {
                    oldObj.setCount(newObj.getCount());
                    return electroShopRepository.save(oldObj);
                }).orElseThrow(EntityNotFOundException::new);
    }
}
