package ru.isands.test.estore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isands.test.estore.models.ElectroEmployee;
import ru.isands.test.estore.models.ElectroEmployeePK;

public interface ElectroEmployeeRepository extends JpaRepository<ElectroEmployee, ElectroEmployeePK> {
}
