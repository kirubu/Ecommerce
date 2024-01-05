package com.gorl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gorl.dto.ProductCategoryDto;
import com.gorl.service.ProductCategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("http://localhost:4200")
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService productCategoryService;
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ProductCategoryDto> addProductCategory(@Valid @RequestBody ProductCategoryDto categoryDto)
	{
		ProductCategoryDto productCategoryDto = productCategoryService.addCategory(categoryDto);
		ResponseEntity<ProductCategoryDto> responseEntity = new ResponseEntity<ProductCategoryDto>(productCategoryDto,HttpStatus.CREATED);
		return responseEntity;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ProductCategoryDto>> getAllCategory()
	{
		List<ProductCategoryDto> dto = productCategoryService.getAllCategory();
		ResponseEntity<List<ProductCategoryDto>> response = new ResponseEntity<List<ProductCategoryDto>>(dto,HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCategory(@PathVariable long id)
	{
		String response = productCategoryService.deleteCategory(id);
		
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(response,HttpStatus.OK);
		
		return responseEntity;
		
	}
}
