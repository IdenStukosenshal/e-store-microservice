package ru.isands.test.estore.restControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.isands.test.estore.exceptions.EntityNotFOundException;
import ru.isands.test.estore.models.Employee;
import ru.isands.test.estore.repositories.EmployeeRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Employees", description = "Сервис для выполнения операций над таблицей сотрудников магазина")
@RequestMapping("rest/employees")
public class EmployeeRestController {

	private final EmployeeRepository employeeRepository;

	public EmployeeRestController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/test")
	@Operation(summary = "Тестовый метод", responses = {
		@ApiResponse(description = "Тестовая фраза")
	})
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("test");
	}


	@GetMapping("/")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}

	@GetMapping("/{id}")
	public Employee getById(@PathVariable Long id){
		return employeeRepository.findById(id).orElseThrow(EntityNotFOundException::new);
	}

	@PostMapping("/")
	public Employee addNewEmployee(@Valid @RequestBody Employee obj){
		return employeeRepository.save(obj);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id){
		if(employeeRepository.existsById(id)){
			employeeRepository.deleteById(id);
		}
	}

	@DeleteMapping("/")
	public void deleteAll(){
		employeeRepository.deleteAll();
	}

	@PutMapping("/")
	public Employee update(@Valid @RequestBody Employee newObj){
		return employeeRepository
				.findById(newObj.getId())
				.map(oldObj->{
					oldObj.setFirstName(newObj.getFirstName());
					oldObj.setLastName(newObj.getLastName());
					oldObj.setPatronymic(newObj.getPatronymic());
					oldObj.setGender(newObj.isGender());
					oldObj.setBirthDate(newObj.getBirthDate());
					oldObj.setPositionId(newObj.getPositionId());
					oldObj.setShopId(newObj.getShopId());
					return employeeRepository.save(oldObj);
				})
				.orElseThrow(EntityNotFOundException::new);
	}
}
