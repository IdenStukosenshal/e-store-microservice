package ru.isands.test.estore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ElectroEmployeePK {

    private Long employeeId;

    private Long electroTypeId;
}
