package com.gorl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorl.Entity.ProductCategory;
import com.gorl.dto.ProductCategoryDto;
import com.gorl.exception.ResourcenotFoundException;
import com.gorl.repo.ProductCategoryRepo;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

	@Autowired
	private ProductCategoryRepo productCategoryRepo;
	
	@Override
	public ProductCategoryDto addCategory(ProductCategoryDto categoryDto) {
		
		ProductCategory category = new ProductCategory();
		category.setCategoryName(categoryDto.getCategoryName());
		
		ProductCategory savedCategory = productCategoryRepo.save(category);
		categoryDto.setId(savedCategory.getId());
		
		return categoryDto;
	}
	
	public List<ProductCategoryDto> getAllCategory(){
		
		 List<ProductCategory> categories = productCategoryRepo.findAll();
		 List<ProductCategoryDto> categoryDtos = new ArrayList<>();
		 
		 for(ProductCategory category : categories)
		 {
			 ProductCategoryDto categoryDto = new ProductCategoryDto();
			 categoryDto.setCategoryName(category.getCategoryName());
			 categoryDto.setId(category.getId());
			 categoryDtos.add(categoryDto);
		 }
		
		return categoryDtos;
		
	}
	
	public String deleteCategory(long id)
	{
		ProductCategory category = productCategoryRepo.findById(id).orElseThrow(
				()->new ResourcenotFoundException("Product Category", "id", id+""));
		
		productCategoryRepo.delete(category);
		
		return category.getCategoryName()+" category deleted successfully";
	}
	
	public ProductCategory getCategoryByName(String name)
	{
		ProductCategory category = productCategoryRepo.findByCategoryName(name).orElseThrow(
				() -> new ResourcenotFoundException("Product category", "Category Name", name)
				);
		return category;
	}
}
