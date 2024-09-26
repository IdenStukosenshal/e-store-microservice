package ru.isands.test.estore.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "electro_item")
public class ElectroItem {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "e_type_id", nullable = false)
    private Long eTypeId;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "count_", nullable = false)
    private Integer count;

    @Column(name = "archive", nullable = false)
    private Boolean archive;

    @Column(name = "description" )
    private String description;
}

