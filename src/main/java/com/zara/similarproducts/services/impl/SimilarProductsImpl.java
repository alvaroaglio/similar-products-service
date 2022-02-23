package com.zara.similarproducts.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zara.similarproducts.mapper.ProductDetailMapper;
import com.zara.similarproducts.models.ProductDetail;
import com.zara.similarproducts.openapi.model.ProductDetailModel;
import com.zara.similarproducts.repositories.ProductRepositoryFeignClient;
import com.zara.similarproducts.services.interfaces.SimilarProductsService;

@Service
public class SimilarProductsImpl implements SimilarProductsService {

	@Autowired
	private ProductRepositoryFeignClient productRepositoryFeignClient;

	/**
	 * Get similar products by id
	 * 
	 * @param productId String
	 * @return List ProductDetailModel
	 */
	@Override
	public List<ProductDetailModel> getProductSimilar(String productId) {
					
			List<String> productSimilarIds = productRepositoryFeignClient.getProductsSimilar(productId);
			
			if(productSimilarIds.isEmpty()) return new ArrayList<ProductDetailModel>();
			
			List<ProductDetail> productDetailList = productSimilarIds.stream().map(productRepositoryFeignClient::getProductsDetail)
				.collect(Collectors.toCollection(ArrayList::new));
			
			return productDetailList.stream().map(ProductDetailMapper.INSTANCE::productDetailToProductDetailModel)	
			    .collect(Collectors.toCollection(ArrayList::new));
	}
	
}
