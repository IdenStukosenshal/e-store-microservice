package ru.isands.test.estore.restControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.exceptions.EntityNotFOundException;
import ru.isands.test.estore.models.Shop;
import ru.isands.test.estore.repositories.ShopRepository;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Shops", description = "Сервис для выполнения операций над таблицей адресов магазинов ")
@RestController
@RequestMapping("rest/shops")
public class ShopRestController {
    private final ShopRepository shopRepository;

    public ShopRestController(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @GetMapping("/")
    public List<Shop> getAllShops(){
        return shopRepository.findAll();
    }

    @GetMapping("/{id}")
    public Shop getById(@PathVariable Long id){
        return shopRepository.findById(id).orElseThrow(EntityNotFOundException::new);
    }

    @PostMapping("/")
    public Shop addNewShop(@Valid @RequestBody Shop newObj){
        return shopRepository.save(newObj);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        if(shopRepository.existsById(id)){
            shopRepository.deleteById(id);
        } else throw new EntityNotFOundException();
    }

    @DeleteMapping("/")
    public void deleteAllShops(){
        shopRepository.deleteAll();
    }

    @PutMapping("/")
    public Shop update(@Valid @RequestBody Shop newObj){
        return shopRepository
                .findById(newObj.getId())
                .map(oldObj->{
                    oldObj.setName(newObj.getName());
                    oldObj.setAddress(newObj.getAddress());
                    return shopRepository.save(oldObj);
                })
                .orElseThrow(EntityNotFOundException::new);
    }


}
