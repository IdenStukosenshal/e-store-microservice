package ru.isands.test.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.isands.test.estore.models.Shop;

public interface ShopRepository extends CrudRepository<Shop, Long> {
}
