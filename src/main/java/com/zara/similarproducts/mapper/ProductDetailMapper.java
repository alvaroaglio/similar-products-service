package com.zara.similarproducts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.zara.similarproducts.models.ProductDetail;
import com.zara.similarproducts.openapi.model.ProductDetailModel;

@Mapper
public interface ProductDetailMapper {

	ProductDetailMapper INSTANCE = Mappers.getMapper(ProductDetailMapper.class);
	
	public ProductDetailModel productDetailToProductDetailModel(ProductDetail productDetail);
}
