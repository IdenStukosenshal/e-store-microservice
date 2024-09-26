package ru.isands.test.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.isands.test.estore.models.ElectroEmployee;
import ru.isands.test.estore.models.ElectroEmployeePK;

public interface ElectroEmployeeRepository extends CrudRepository<ElectroEmployee, ElectroEmployeePK> {
}
