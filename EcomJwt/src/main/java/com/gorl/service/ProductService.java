package com.gorl.service;

import java.util.List;

import com.gorl.dto.request.ProductDto;

public interface ProductService {
	
	ProductDto createProduct(ProductDto productDto);
	
	List<ProductDto> getAllProduct();
	
	String deleteProductById(Long id);
	
	ProductDto getProductDetailById(long id);
	
	ProductDto updateProduct(long id, ProductDto dto);
}
