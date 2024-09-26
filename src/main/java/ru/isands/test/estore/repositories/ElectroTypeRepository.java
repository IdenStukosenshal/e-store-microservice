package ru.isands.test.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.isands.test.estore.models.ElectroType;


public interface ElectroTypeRepository  extends CrudRepository<ElectroType, Long> {
}
