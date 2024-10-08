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
import javax.validation.constraints.NotNull;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "purchase")
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Идентификатор покупки
     */
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "purchase_counter")
    @TableGenerator(name = "purchase_counter", pkColumnName = "name", pkColumnValue = "ru.isands.test.estore.models.Purchase", table = "counter", valueColumnName = "currentid", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    /**
     * Идентификатор товара
     */
    @NotNull
    @Column(name = "electro_id", nullable = false)
    private Long electroId;

    /**
     * Идентификатор сотрудника
     */
    @NotNull
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    /**
     * Дата совершения покупки
     */
    @NotNull
    @Column(name = "purchase_date", nullable = false)
    private Date purchaseDate;

    /**
     * Способ оплаты
     */
    @NotNull
    @Column(name = "type_id", nullable = false)
    private Integer type;

    /**
     * Идентификатор магазина
     */
    @NotNull
    @Column(name = "shop_id", nullable = false)
    private Long shopId;

}