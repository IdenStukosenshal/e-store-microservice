package ru.isands.test.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.isands.test.estore.models.PositionType;

public interface PositionTypeRepository extends CrudRepository<PositionType, Long> {
}
