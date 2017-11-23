package com.capgemini.jpa2project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.exceptions.BusinessException;
import com.capgemini.jpa2project.exceptions.ExceptionMessages;
import com.capgemini.jpa2project.mapper.ProductMapper;
import com.capgemini.jpa2project.service.ProductEntityService;
import com.capgemini.jpa2project.to.ProductTo;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "spring.profiles.active=mysql", "spring.datasource.username=root",
"spring.datasource.password=Qwerty123" })
@Transactional
public class ProductServiceTest {
	
	@Autowired
	private ProductEntityService service;
	@Autowired
	private ProductMapper mapper;
	
	@Test
	public void shouldFindProduct() throws BusinessException {
		//given
		Long id = 20L;
		//when
		ProductEntity entity = service.findOne(id);
		//then
		assertEquals("Lamb - Bones",entity.getProductName());	
	}
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	
	@Test
	public void shouldFindAllProductLists() {
		// given
		int size = 50;
		// when
		int listSize = service.findAll().size();
		// then
		assertEquals(size, listSize);

	}

	@Test
	public void shouldNotFindTheProductListSize() {
		// given
		int size = 100;
		// when
		int listSize = service.findAll().size();
		// then
		assertNotEquals(size, listSize);

	}

	@Test
	public void shouldFindOneProduct() throws BusinessException {
		// given
		Long id = 7L;
		// when
		ProductEntity product = service.findOne(id);
		// then
		assertEquals(id, product.getId());

	}

	@Test
	public void shouldNotFindOneProduct() throws BusinessException {
		// given
		Long id = 2777L;
		// when
		expectedEx.expect(BusinessException.class);
		expectedEx.expectMessage(ExceptionMessages.OBJECT_NOT_FOUND);
		service.findOne(id);
	}

	@Test
	public void shouldCreateNewProductListAndAddItToDatabase() throws BusinessException {
		// given
		Long id = 51L;
		ProductEntity entity = new ProductEntity();
		ProductTo productTo = ProductTo.builder().margin(new BigDecimal("8.78")).productName("Nutella").unitPrice(new BigDecimal("10.24"))
				.build();
		int productListSize = service.findAll().size();
		// when
		service.createOne(entity, productTo);
		int productListSizeWithNewProduct = service.findAll().size();
		// then
		assertEquals(productListSize+1, productListSizeWithNewProduct);
		assertEquals(id, entity.getId());
	}

	@Test
	public void shouldNotCreateNewProductListAndAddItToDatabase() throws BusinessException {
		// given
		ProductTo productTo = ProductTo.builder().margin(new BigDecimal("8.78")).productName("Nutella").id(51L).unitPrice(new BigDecimal("10.24"))
				.build();
		// when
		ProductEntity entity = new ProductEntity();
		expectedEx.expect(BusinessException.class);
		expectedEx.expectMessage(ExceptionMessages.ID_SET_MANUALLY);
		// then
		service.createOne(entity, productTo);
	}
	
	@Test
	public void shouldUpdateProductList() throws BusinessException {
		// given
		Long entityId = 16L;
		ProductEntity entity = service.findOne(entityId);
		ProductTo newEntity =  ProductTo.builder().margin(new BigDecimal("8.78")).productName("Nutella").unitPrice(new BigDecimal("10.24"))
				.build();
		// when
		service.updateOne(entity, newEntity);
		// then
		assertEquals(entityId, entity.getId());
		assertEquals(entity.getMargin(), newEntity.getMargin());
	}
	
	@Test
	public void shouldCheckIfUpdateUpdatesProductListAndNotAddsNew() throws BusinessException {
		// given
		
		Long entityId = 16L;
		ProductEntity entity = service.findOne(entityId);
		ProductTo newEntity =  ProductTo.builder().margin(new BigDecimal("8.78")).productName("Nutella").unitPrice(new BigDecimal("10.24"))
				.build();
		// when
		service.updateOne(entity, newEntity);
		
		int productListSize = service.findAll().size();
		// then
		assertEquals(50, productListSize);
	}

	@Test
	public void shouldNotUpdateProductList() throws BusinessException {
		// given
		Long entityId = 51L;
		expectedEx.expect(BusinessException.class);
		expectedEx.expectMessage(ExceptionMessages.OBJECT_NOT_FOUND);
		ProductEntity entity = service.findOne(entityId);
		ProductTo newEntity =  ProductTo.builder().margin(new BigDecimal("8.78")).productName("Nutella").unitPrice(new BigDecimal("10.24"))
				.build();
		// when
		
		service.updateOne(entity, newEntity);
		

	}
	
	@Test
	public void shouldDeleteProduct() throws BusinessException {
		// given
		
		ProductTo to = ProductTo.builder().id(20L).build();
		int productListSize = service.findAll().size();
		// when
		service.deleteOne(to);
		int newProductListSize = service.findAll().size();
		// then
		assertEquals(productListSize-1, newProductListSize);
	}
	
	@Test
	public void shouldNotDeleteProductList() throws BusinessException {
		// given
		ProductTo to = ProductTo.builder().id(51L).build();
		int productListSize = service.findAll().size();
		// when
		expectedEx.expect(BusinessException.class);
		expectedEx.expectMessage(ExceptionMessages.ID_NOT_VALID);
		service.deleteOne(to);
	
		int newProductListSize = service.findAll().size();
		// then
		assertEquals(productListSize-1, newProductListSize);
	}
	
	
	
	
}
