package com.capgemini.jpa2project.to;

import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.domain.TransactionEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductListTo extends AbstractTo {

	private int numberOfItems;
	private TransactionEntity transaction;
	private ProductEntity product;

	@Builder
	public ProductListTo(Long id, long Version, int numberOfItems, TransactionEntity transaction,
			ProductEntity product) {
		super(id, Version);
		this.numberOfItems = numberOfItems;
		this.transaction = transaction;
		this.product = product;
	}

}
