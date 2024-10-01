package ru.isands.test.estore.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter

@Entity
@Table(name = "electro_item")
public class ElectroItem {

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @NotNull
    @Column(name = "e_type_id", nullable = false)
    private Long eTypeId;

    @NotNull
    @Column(name = "price", nullable = false)
    private Long price;

    @NotNull
    @Column(name = "count_", nullable = false)
    private Integer count;

    @NotNull
    @Column(name = "archive", nullable = false)
    private Boolean archive;

    @Column(name = "description" )
    private String description;
}

