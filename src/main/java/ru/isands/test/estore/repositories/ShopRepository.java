package ru.isands.test.estore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.models.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
