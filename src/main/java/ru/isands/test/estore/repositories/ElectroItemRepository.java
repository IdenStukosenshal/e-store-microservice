package ru.isands.test.estore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.models.ElectroItem;

public interface ElectroItemRepository extends JpaRepository<ElectroItem, Long> {
}
