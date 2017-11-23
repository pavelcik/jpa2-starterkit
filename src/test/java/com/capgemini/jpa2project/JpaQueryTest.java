package com.capgemini.jpa2project;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.service.JPaQueryService;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "spring.profiles.active=mysql", "spring.datasource.username=root",
		"spring.datasource.password=Qwerty123" })
public class JpaQueryTest {
	
	@Autowired
	private JPaQueryService service;
	
	@Test
	public void shouldCalculateTransaction() {
		//given
		Long id = 2L;
		BigDecimal result = new BigDecimal("290.00");
		//when
		BigDecimal calculationResult = service.calculateTransaction(id);
		//then
		assertEquals(result,calculationResult);
	}

	@Test
	public void shouldCalculateTransactionWithMorethanOneItemBought() {
		//given
		Long id = 18L;
		BigDecimal result = new BigDecimal("262.71");
		//when
		BigDecimal calculationResult = service.calculateTransaction(id);
		//then
		assertEquals(result,calculationResult);
	}
	
	@Test
	public void shouldFindUniqueListOfProductsByClient() {
		Long id = 1L;
		
		
		//when
		List<String> productList = service.uniqueShoppingListByClient(id);
		
		assertEquals("Lamb - Bones",productList.get(0));
		assertEquals("Pastry - Apple Large",productList.get(1));
	}
	
	@Test
	public void shouldFindTop10() {
		List<ProductEntity> top10 = service.tenBestsellingProducts();
	}
	
}
