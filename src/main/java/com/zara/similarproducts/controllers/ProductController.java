package com.zara.similarproducts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.zara.similarproducts.openapi.api.ProductApi;
import com.zara.similarproducts.openapi.model.ProductDetailModel;
import com.zara.similarproducts.services.interfaces.SimilarProductsService;

import feign.FeignException.NotFound;
import lombok.extern.java.Log;

@RestController
@Log
public class ProductController implements ProductApi{

	@Autowired
	private SimilarProductsService productService;
	
	@Override
	public ResponseEntity<List<ProductDetailModel>> getProductSimilar(String productId){
		
		log.info("Searching similar products");
		
		try {
			
			return new ResponseEntity<List<ProductDetailModel>>(productService.getProductSimilar(productId), HttpStatus.OK);
			
		} catch (NotFound exNotFound) {
			log.severe("Error Not Found: " + exNotFound.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exception: ", exNotFound);
		} catch (Exception e) {
			log.severe("Error Service: " + e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Exception: ", e);
		}	
	}	
}
