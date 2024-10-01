package ru.isands.test.estore.restControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.exceptions.EntityNotFOundException;
import ru.isands.test.estore.models.Purchase;
import ru.isands.test.estore.repositories.PurchaseRepository;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Purchases", description = "Сервис для выполнения операций над таблицей покупок")
@RestController
@RequestMapping("rest/purchases")
public class PurchaseRestController {
    private final PurchaseRepository purchaseRepository;

    public PurchaseRestController(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }


    @GetMapping("/")
    public List<Purchase> getAllPurchases(){
        return purchaseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Purchase getById(@PathVariable Long id){
        return purchaseRepository.findById(id).orElseThrow(EntityNotFOundException::new);
    }

    //Добавить проверку на наличие товара здесь?
    @PostMapping("/")
    public Purchase addNewPurchase(@Valid @RequestBody Purchase newObj){
        return purchaseRepository.save(newObj);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        if(purchaseRepository.existsById(id)){
            purchaseRepository.deleteById(id);
        } else throw new EntityNotFOundException();
    }

    @DeleteMapping("/")
    public void deleteAllPurchases(){
        purchaseRepository.deleteAll();
    }

    //???
    @PutMapping("/")
    public Purchase update(@Valid @RequestBody Purchase newObj){
        return purchaseRepository
                .findById(newObj.getId())
                .map(oldObj->{
                    oldObj.setShopId(newObj.getShopId());
                    oldObj.setType(newObj.getType());
                    oldObj.setPurchaseDate(newObj.getPurchaseDate());
                    oldObj.setElectroId(newObj.getElectroId());
                    oldObj.setEmployeeId(newObj.getEmployeeId());
                    return purchaseRepository.save(oldObj);
                })
                .orElseThrow(EntityNotFOundException::new);
    }
}
