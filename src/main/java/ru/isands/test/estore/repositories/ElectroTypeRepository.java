package ru.isands.test.estore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.models.ElectroType;


public interface ElectroTypeRepository extends JpaRepository<ElectroType, Long> {
}
