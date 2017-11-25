package com.capgemini.jpa2project.to;

import java.math.BigDecimal;
import java.util.List;

import com.capgemini.jpa2project.domain.ProductListEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductTo extends AbstractTo {

	private BigDecimal unitPrice;
	private String productName;
	private BigDecimal margin;
	private List<ProductListEntity> orderedProducts;

	@Builder
	public ProductTo(Long id, long Version, BigDecimal unitPrice, String productName, BigDecimal margin,
			List<ProductListEntity> orderedProducts) {
		super(id, Version);
		this.unitPrice = unitPrice;
		this.productName = productName;
		this.margin = margin;
		this.orderedProducts = orderedProducts;
	}

}
