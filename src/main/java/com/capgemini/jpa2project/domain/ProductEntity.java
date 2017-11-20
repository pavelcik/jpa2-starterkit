package com.capgemini.jpa2project.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class ProductEntity extends AbstractEntity {

	private float unitPrice;
	private int numberOfItems; 
	private String productName;
	@Column(precision=3, scale=2)
	private BigDecimal margin;
	
	@OneToMany(cascade=CascadeType.REMOVE,orphanRemoval=true,mappedBy="product")
	private List<TransactionEntity> transactions;
	
	
	
}
