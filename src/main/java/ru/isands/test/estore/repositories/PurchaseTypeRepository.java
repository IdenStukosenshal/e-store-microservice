package ru.isands.test.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.isands.test.estore.models.PurchaseType;

public interface PurchaseTypeRepository extends CrudRepository<PurchaseType, Long> {
}
