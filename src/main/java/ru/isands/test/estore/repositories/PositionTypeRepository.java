package ru.isands.test.estore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.models.PositionType;

public interface PositionTypeRepository extends JpaRepository<PositionType, Long> {
}
