package com.capgemini.jpa2project.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.to.ProductTo;

@Component
public class ProductMapper {
	
	public ProductTo map(ProductEntity productEntity) {
		ProductTo product = null;
				
				
		if (productEntity != null) {
			product = new ProductTo.Builder().id(productEntity.getId())
					.margin(productEntity.getMargin()).productName(productEntity.getProductName())
					.unitPrice(productEntity.getUnitPrice()).build();
		}
		return product;
	}
	
	public ProductEntity map(ProductTo productTo) {
		
		ProductEntity productEntity = new ProductEntity();
		
		if (productEntity != null) {
			productEntity.setId(productTo.getId());
			productEntity.setMargin(productTo.getMargin());
			productEntity.setProductName(productTo.getProductName());
			productEntity.setUnitPrice(productTo.getUnitPrice());
			productEntity.setVersion(productTo.getVersion());
		}
		return productEntity;
	}
	
	public List<ProductTo> map2To(List<ProductEntity> productEntities) {
		return productEntities.stream().map(c->map(c)).collect(Collectors.toList());
	}
}
