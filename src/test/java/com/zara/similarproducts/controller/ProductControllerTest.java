package com.zara.similarproducts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zara.similarproducts.clients.ProductClient;
import com.zara.similarproducts.controllers.ProductController;
import com.zara.similarproducts.mapper.ProductDetailMapper;
import com.zara.similarproducts.models.ProductDetail;

@SpringBootTest
public class ProductControllerTest {

	private MockMvc mvc;
	
	@Autowired
	private ProductController productController;
	
	@MockBean
	private ProductClient productClient;
	
	private ObjectMapper mapper;
	
	@BeforeEach
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(productController).build();
		this.mapper = new ObjectMapper();
	}
	
	/**
	 * Integration test similar products
	 * 
	 * @throws Exception
	 */
	@Test
	public void integrationTest() throws Exception {
		
		when(productClient.getProductsSimilarIds(Mockito.anyString())).thenReturn(buildProductsSimilar());
		when(productClient.getProductDetail("2")).thenReturn(buildProductDetail1());
		when(productClient.getProductDetail("3")).thenReturn(buildProductDetail2());
		when(productClient.getProductDetail("4")).thenReturn(buildProductDetail3());
		
		ResultActions response = mvc.perform(get("/product/1/similar").contentType(MediaType.APPLICATION_JSON));
		response.andExpect(status().isOk());
		
		String responseContent = response.andReturn().getResponse().getContentAsString();
		String expected = mapper.writeValueAsString(List.of(ProductDetailMapper.INSTANCE.productDetailToProductDetailModel(buildProductDetail1()),
				ProductDetailMapper.INSTANCE.productDetailToProductDetailModel(buildProductDetail2()),
				ProductDetailMapper.INSTANCE.productDetailToProductDetailModel(buildProductDetail3()))); 
		
		assertEquals(responseContent, expected);		
		
	}
	
	private List<String> buildProductsSimilar() {
		return List.of("2", "3", "4");
	}
	
	private ProductDetail buildProductDetail1() {
		return ProductDetail.builder().id("2").name("Dress").price(BigDecimal.valueOf(19,99)).availability(true).build();
	}
	
	private ProductDetail buildProductDetail2() {
		return ProductDetail.builder().id("3").name("Blazer").price(BigDecimal.valueOf(29,99)).availability(false).build();
	}
	
	private ProductDetail buildProductDetail3() {
		return ProductDetail.builder().id("4").name("Boots").price(BigDecimal.valueOf(39.99)).availability(true).build();
	}

}
	
