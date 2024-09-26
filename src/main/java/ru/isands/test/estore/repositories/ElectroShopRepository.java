package ru.isands.test.estore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.isands.test.estore.models.ElectroShop;
import ru.isands.test.estore.models.ElectroShopPK;

public interface ElectroShopRepository extends JpaRepository<ElectroShop, ElectroShopPK> {
	
}
