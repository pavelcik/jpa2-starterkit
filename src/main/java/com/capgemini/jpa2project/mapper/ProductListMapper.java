package com.capgemini.jpa2project.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.domain.ProductListEntity;
import com.capgemini.jpa2project.to.ProductListTo;
@Component
public class ProductListMapper {
	
	public ProductListTo map(ProductListEntity productListEntity) {
		ProductListTo productTo = null;
				
				
		if (productListEntity != null) {
			productTo = ProductListTo.builder().numberOfItems(productListEntity.getNumberOfItems()).product(productListEntity.getProduct())
					.transaction(productListEntity.getTransaction()).
					id(productListEntity.getId()).Version(productListEntity.getVersion()).build();
		}
		return productTo;
	}
	
	public ProductListEntity map(ProductListEntity productListEntity, ProductListTo productListTo) {
		
		if (productListEntity != null) {
			productListEntity.setNumberOfItems(productListTo.getNumberOfItems());
			return productListEntity;
		} else {
			ProductListEntity productListEntity2 = new ProductListEntity();
			productListEntity2.setNumberOfItems(productListTo.getNumberOfItems());
			productListEntity2.setVersion(productListTo.getVersion());
			productListEntity2.setProduct(productListTo.getProduct());
			productListEntity2.setTransaction(productListTo.getTransaction());
		}
		return productListEntity;
	}
	
	public List<ProductListTo> map2To(List<ProductListEntity> productListEntities) {
		return productListEntities.stream().map(c->map(c)).collect(Collectors.toList());
	}
}
