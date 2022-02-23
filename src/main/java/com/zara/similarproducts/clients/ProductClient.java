package com.zara.similarproducts.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zara.similarproducts.models.ProductDetail;

@FeignClient(name = "ProductClient", url = "${url.server.existingApis}")
public interface ProductClient {

	/**
	 * Get similar products by id
	 * 
	 * @param productId String
	 * @return List String
	 */
	@RequestMapping(method = RequestMethod.GET, value = "${url.server.existingApis.getProductSimilarIds}", consumes = "application/json")
	public List<String> getProductsSimilarIds(@PathVariable String productId);
	
	/**
	 * Get product detail by id
	 * 
	 * @param productId String
	 * @return ProductDetail
	 */
	@RequestMapping(method = RequestMethod.GET, value = "${url.server.existingApis.getProductDetail}", consumes = "application/json")
	public ProductDetail getProductDetail(@PathVariable String productId);
}
