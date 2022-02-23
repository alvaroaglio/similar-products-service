package com.zara.similarproducts.models;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetail {

	private String id;

	private String name;

	private BigDecimal price;

	private Boolean availability;
	
}
