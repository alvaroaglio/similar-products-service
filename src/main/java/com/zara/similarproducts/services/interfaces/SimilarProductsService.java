package com.zara.similarproducts.services.interfaces;

import java.util.List;

import com.zara.similarproducts.openapi.model.ProductDetailModel;

public interface SimilarProductsService {

	List<ProductDetailModel> getProductSimilar(String productId) throws Exception;
}
