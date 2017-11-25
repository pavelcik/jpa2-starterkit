package com.capgemini.jpa2project.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Product")
public class ProductEntity extends AbstractEntity {

	private BigDecimal unitPrice;
	
	private String productName;
	@Column(precision=3, scale=2)
	private BigDecimal margin;
	
	
	@OneToMany(cascade=CascadeType.REMOVE)
	@JoinColumn(name="product_id")
	private List<ProductListEntity> orderedProducts;


	

	
}
