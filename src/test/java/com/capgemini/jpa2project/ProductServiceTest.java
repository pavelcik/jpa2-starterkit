package com.capgemini.jpa2project;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.exceptions.BusinessException;
import com.capgemini.jpa2project.exceptions.ExceptionMessages;
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
	
	@Test
	public void shouldFindObject() throws BusinessException {
		//given
		Long id = 20L;
		//when
		ProductEntity entity = service.findOne(id);
		//then
		assertEquals("Lamb - Bones",entity.getProductName());
		
	}
	
	@Test(expected=BusinessException.class)
	public void shouldNotFindObject() throws BusinessException {
		//given
		Long id = 51L;
		//when
		ProductEntity entity = service.findOne(id);
	}
	
	@Test
	public void shouldCreateObject() throws BusinessException {
		//given
		ProductTo product = new ProductTo.Builder().id(51L).margin(new BigDecimal("8.25")).productName("Soap - Dove").unitPrice(2.24f).build();
		int size = service.findAll().size();
		//when
		service.createOne(product);
		//then
		assertEquals(size+1,service.findAll().size());
		
	}
	
	
	
}
