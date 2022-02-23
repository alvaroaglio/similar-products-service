package com.zara.similarproducts.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zara.similarproducts.clients.ProductClient;
import com.zara.similarproducts.models.ProductDetail;

import feign.FeignException;
import feign.FeignException.NotFound;
import lombok.extern.java.Log;

@Component
@Log
public class ProductRepositoryFeignClient {

	@Autowired
	private ProductClient productClient;

	/**
	 * Get similar products by id
	 * 
	 * @param productId
	 * @return List String
	 */
	public List<String> getProductsSimilar(String productId) {

		log.info("Sending request similar products with product id: " + productId);
		
		try {
			return productClient.getProductsSimilarIds(productId);
		} catch (NotFound exNotFound) {
			throw exNotFound;
		} catch (FeignException exFeignClient) {
			throw exFeignClient;
		}

	}

	/**
	 * Get product detail by id
	 * 
	 * @param productId String
	 * @return ProductDetail
	 */
	public ProductDetail getProductsDetail(String productId) {

		log.info("Sending request product detail with product id: " + productId);
		
		try {
			return productClient.getProductDetail(productId);
		} catch (NotFound exNotFound) {
			throw exNotFound;
		} catch (FeignException exFeignClient) {
			throw exFeignClient;
		}
	}
}
