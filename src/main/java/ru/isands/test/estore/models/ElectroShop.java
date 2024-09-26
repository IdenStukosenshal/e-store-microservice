package ru.isands.test.estore.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

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
	@Id
	@Column(name = "shop_id", nullable = false)
	private Long shopId;
	
	/**
	 * Идентификатор электротовара
	 */
	@Id
	@Column(name = "electro_item_id", nullable = false)
	private Long electroItemId;
	
	/**
	 * Оставшееся количество
	 */
	@Column(name = "count_", nullable = false)
	private Integer count;
}
