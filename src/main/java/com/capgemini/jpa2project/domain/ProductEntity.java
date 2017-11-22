package com.capgemini.jpa2project.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class ProductEntity extends AbstractEntity {

	private float unitPrice;
	
	private String productName;
	@Column(precision=3, scale=2)
	private BigDecimal margin;
	
	
	@OneToMany(cascade=CascadeType.REMOVE,orphanRemoval=true)
	@JoinColumn(name="product_id")
	private List<ProductListEntity> orderedProducts;


	public float getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public BigDecimal getMargin() {
		return margin;
	}


	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}


	public List<ProductListEntity> getOrderedProducts() {
		return orderedProducts;
	}


	public void setOrderedProducts(List<ProductListEntity> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}
	
	

	
}
