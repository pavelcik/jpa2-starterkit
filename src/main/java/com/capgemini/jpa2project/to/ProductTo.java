package com.capgemini.jpa2project.to;

import java.math.BigDecimal;
import java.util.List;

import com.capgemini.jpa2project.domain.ProductListEntity;

public class ProductTo extends AbstractTo {

	private float unitPrice;
	private String productName;
	private BigDecimal margin;
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

	public static class Builder {
		private float unitPrice;
		private String productName;
		private BigDecimal margin;
		private Long id;

		public Builder unitPrice(float unitPrice) {
			this.unitPrice = unitPrice;
			return this;
		}

		public Builder productName(String productName) {
			this.productName = productName;
			return this;
		}

		public Builder margin(BigDecimal margin) {
			this.margin = margin;
			return this;
		}
		
		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public ProductTo build() {
			return new ProductTo(this);
		}
	}

	private ProductTo(Builder builder) {
		this.unitPrice = builder.unitPrice;
		this.productName = builder.productName;
		this.margin = builder.margin;
	}
}
