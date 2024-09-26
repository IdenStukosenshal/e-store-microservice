package ru.isands.test.estore.models;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Идентификатор сотрудника
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_counter")
	@TableGenerator(name = "employee_counter", pkColumnName = "name", pkColumnValue = "ru.isands.test.estore.models.Employee", table = "counter", valueColumnName = "currentid", allocationSize = 2)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	/**
	 * Фамилия сотрудника
	 */
	@Column(name = "last_name", nullable = false, length = 100)
	private String lastName;
	
	/**
	 * Имя сотрудника
	 */
	@Column(name = "first_name", nullable = false, length = 100)
	private String firstName;
	
	/**
	 * Отчество сотрудника
	 */
	@Column(name = "patronymyc", nullable = false, length = 100)
	private String patronymic;
	
	/**
	 * Дата рождения сотрудника
	 */
	@Column(name = "birth_date", nullable = false)
	private Date birthDate;
	
	/**
	 * Ссылка на должность сотрудника
	 */
	@Column(name = "position_id", nullable = false)
	private Long positionId;

	/**
	 * Идентификатор магазина
	 */
	@Column(name = "shop_id", nullable = false)
	private Long shopId;
	
	/**
	 * Пол сотрудника (true - мужской, false - женский)
	 */
	@Column(name = "gender", nullable = false)
	private boolean gender;

}