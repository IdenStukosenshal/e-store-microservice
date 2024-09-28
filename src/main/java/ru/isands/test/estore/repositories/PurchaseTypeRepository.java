package ru.isands.test.estore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.models.PurchaseType;

public interface PurchaseTypeRepository extends JpaRepository<PurchaseType, Long> {
}
