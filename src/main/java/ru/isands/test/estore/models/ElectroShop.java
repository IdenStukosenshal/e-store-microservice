package ru.isands.test.estore.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@IdClass(ElectroShopPK.class)
@Table(name = "electro_shop")
public class ElectroShop {
	
	/**
	 * Идентификатор магазина
	 */
	@NotNull
	@Id
	@Column(name = "shop_id", nullable = false)
	private Long shopId;
	
	/**
	 * Идентификатор электротовара
	 */
	@NotNull
	@Id
	@Column(name = "electro_item_id", nullable = false)
	private Long electroItemId;
	
	/**
	 * Оставшееся количество
	 */
	@NotNull
	@Column(name = "count_", nullable = false)
	private Integer count;
}
