package ru.isands.test.estore.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.IdClass;
import javax.validation.constraints.NotNull;

@Getter
@Setter

@Entity
@Table(name = "electro_employee")
@IdClass(ElectroEmployeePK.class)
public class ElectroEmployee {

    @NotNull
    @Id
    @Column(name = "employee_id", nullable = false )
    private Long employeeId;

    @NotNull
    @Id
    @Column(name = "electro_type_id", nullable = false)
    private Long electroTypeId;
}

