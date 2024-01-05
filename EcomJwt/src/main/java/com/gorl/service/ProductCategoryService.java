package com.gorl.service;

import java.util.List;

import com.gorl.Entity.ProductCategory;
import com.gorl.dto.ProductCategoryDto;

public interface ProductCategoryService {

	public ProductCategoryDto addCategory(ProductCategoryDto categoryDto);
	
	List<ProductCategoryDto> getAllCategory();
	
	String deleteCategory(long id);
	
	ProductCategory getCategoryByName(String name);
}
