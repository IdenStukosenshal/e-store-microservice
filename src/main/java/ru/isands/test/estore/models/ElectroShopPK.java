package ru.isands.test.estore.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ElectroShopPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 *  Идентификатор магазина
	 */
	private Long shopId;
	
	/**
	 *  Идентификатор электротовара
	 */
	private Long electroItemId;

}
