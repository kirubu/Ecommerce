package com.gorl.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorl.Entity.Product;
import com.gorl.Entity.ProductCategory;
import com.gorl.dto.request.ProductDto;
import com.gorl.exception.ResourcenotFoundException;
import com.gorl.repo.ProductCategoryRepo;
import com.gorl.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ProductCategoryRepo categoryRepo;
	
	@Autowired
	private ProductCategoryService categoryService;
	
	@Override
	public ProductDto createProduct(ProductDto productDto) {

		ProductCategory category = categoryService.getCategoryByName(productDto.getProductCategory());
		
		Product productEntity = convertDtoToEntity(productDto);
		
		productEntity.setProductCategory(category);
		
		productEntity = productRepo.save(productEntity);
		productDto.setId(productEntity.getId());
		
		return productDto;
	}
	
	public List<ProductDto> getAllProduct()
	{
		List<Product> entityList = productRepo.findAll();
		List<ProductDto> dtoList = new ArrayList<>();
		
		for (Product product : entityList) {
			ProductDto dto = convertEntityToDto(product);
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	public String deleteProductById(Long id)
	{
		Product product = productRepo.findById(id).orElseThrow(
				() -> new ResourcenotFoundException("Product", "id", ""+id));
		
		productRepo.delete(product);
		
		return product.getName()+" deleted successfully";
	}
	
	//get product by id
	public ProductDto getProductDetailById(long id)
	{
		Product product = productRepo.findById(id).orElseThrow(
				() -> new ResourcenotFoundException("Product", "id", ""+id));
		
		ProductDto dto = convertEntityToDto(product);
		//System.out.println(dto);
		return dto;
	}
	
	
	public ProductDto updateProduct(long id, ProductDto dto)
	{
		// Find the existing product by id or throw an exception if not found
	    Product productEntity = productRepo.findById(id)
	            .orElseThrow(() -> new ResourcenotFoundException("Product", "id", String.valueOf(id)));

	    // Get the ProductCategory from the categoryService using the name from the dto
	    ProductCategory category = categoryService.getCategoryByName(dto.getProductCategory());

	    // Update fields of the existing product entity
	    productEntity.setName(dto.getName());
	    productEntity.setDescription(dto.getDescription());
	    productEntity.setStockQuantity(dto.getStockQuantity());
	    productEntity.setPrice(dto.getPrice());
	    productEntity.setDiscountPercent(dto.getDiscountPercent());
	    productEntity.setColor(dto.getColor());
	    productEntity.setProductCategory(category);
	    productEntity.setImageUrl(dto.getImageUrl());

	    // Save the updated product entity to the repository
	    productEntity = productRepo.save(productEntity);

	    // Convert the updated Product entity back to a ProductDto
	    dto = convertEntityToDto(productEntity);

	    return dto;
	}
	
	// // // // // //
	ProductDto convertEntityToDto(Product entity)
	{
		ProductDto dto = new ProductDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setColor(entity.getColor());
		dto.setDiscountPercent(entity.getDiscountPercent());
		dto.setPrice(entity.getPrice());
		dto.setColor(entity.getColor());
		dto.setProductCategory(entity.getProductCategory().getCategoryName());
		dto.setImageUrl(entity.getImageUrl());
		dto.setStockQuantity(entity.getStockQuantity());
		return dto;
	}
	
	Product convertDtoToEntity(ProductDto dto){
		
		Product entity = new Product();
		
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setStockQuantity(dto.getStockQuantity());
		entity.setPrice(dto.getPrice());
		entity.setDiscountPercent(dto.getDiscountPercent());
		entity.setColor(dto.getColor());
		entity.setImageUrl(dto.getImageUrl());
		
		return entity;
	}
}
