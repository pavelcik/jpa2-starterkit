package com.capgemini.jpa2project;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jpa2project.domain.ClientEntity;
import com.capgemini.jpa2project.domain.OrderStatus;
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
		assertEquals(10,top10.size());
	}
	
	@Test
	public void shouldFind3ClientsWithPriciestCart() {
		LocalDate fromLocal = LocalDate.of(2016, 3, 1);
		LocalDate toLocal = LocalDate.of(2016, 3, 31);
		Date from = Date.valueOf(fromLocal);
		Date to = Date.valueOf(toLocal);
		List<ClientEntity> result = service.moneySpentByClient(from,to);
		System.out.println("lol");
	}
	
	@Test
	public void shouldFindProductsWithProcessingStatus() {
		Long id = 20L;
		List<ProductEntity> productsInProcessingStatus = service.productsInRealisation();
		assertEquals(16, productsInProcessingStatus.size());
		assertEquals(id, productsInProcessingStatus.get(0).getId());
		assertEquals(OrderStatus.PROCESSING, productsInProcessingStatus.get(0).getOrderedProducts().get(0).getTransaction().getStatus());
	}
	
}