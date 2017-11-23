package com.capgemini.jpa2project.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="product_list")
public class ProductListEntity extends AbstractEntity {

	private int numberOfItems;
	
	@ManyToOne
	private TransactionEntity transaction;
	@ManyToOne
	private ProductEntity product;

	
	
}
