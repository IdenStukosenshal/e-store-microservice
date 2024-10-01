package ru.isands.test.estore.restControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.exceptions.EntityNotFOundException;
import ru.isands.test.estore.models.PurchaseType;
import ru.isands.test.estore.repositories.PurchaseTypeRepository;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Purchase Types", description = "Сервис для выполнения операций над таблицей типов покупок")
@RestController
@RequestMapping("rest/purchase-types")
public class PurchaseTypeRestController {

    private final PurchaseTypeRepository purchaseTypeRepository;

    public PurchaseTypeRestController(PurchaseTypeRepository purchaseTypeRepository) {
        this.purchaseTypeRepository = purchaseTypeRepository;
    }


    @GetMapping("/")
    public List<PurchaseType> getAllPurchaseTypes(){
        return purchaseTypeRepository.findAll();
    }

    @GetMapping("/{id}")
    public PurchaseType getById(@PathVariable Long id){
        return purchaseTypeRepository.findById(id).orElseThrow(EntityNotFOundException::new);
    }

    @PostMapping("/")
    public PurchaseType addNewPurchaseType(@Valid @RequestBody PurchaseType newObj){
        return purchaseTypeRepository.save(newObj);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        if(purchaseTypeRepository.existsById(id)){
            purchaseTypeRepository.deleteById(id);
        } else throw new EntityNotFOundException();
    }

    @DeleteMapping("/")
    public void deleteAllPurchaseTypes(){
        purchaseTypeRepository.deleteAll();
    }

    @PutMapping("/")
    public PurchaseType update(@Valid @RequestBody PurchaseType newObj){
        return purchaseTypeRepository
                .findById(newObj.getId())
                .map(oldObj->{
                    oldObj.setName(newObj.getName());
                    return purchaseTypeRepository.save(oldObj);
                })
                .orElseThrow(EntityNotFOundException::new);
    }
}