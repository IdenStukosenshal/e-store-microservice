package ru.isands.test.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.isands.test.estore.models.ElectroItem;

public interface ElectroItemRepository  extends CrudRepository<ElectroItem, Long> {
}
