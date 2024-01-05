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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gorl.dto.request.ProductDto;
import com.gorl.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("http://localhost:4200")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping(value = "/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto dto)
	{
		ProductDto dtoProduct = productService.createProduct(dto);
		
		ResponseEntity<ProductDto> responseEntity = new ResponseEntity<ProductDto>(dtoProduct,HttpStatus.CREATED);
		
		return responseEntity;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ProductDto>> getAllProductWp()//Wp-Without Paging
	{
		List<ProductDto> listDto = productService.getAllProduct();
		
		ResponseEntity<List<ProductDto>> response = new ResponseEntity<List<ProductDto>>(listDto,HttpStatus.OK);
		
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id)
	{
		 String response = productService.deleteProductById(id);
		 
		 ResponseEntity<String> responseEntity = new ResponseEntity<String>(response, HttpStatus.OK);
		
		 return responseEntity;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductDetailById(@PathVariable long id)
	{
		ProductDto dto = productService.getProductDetailById(id);
		ResponseEntity<ProductDto> response = new ResponseEntity<ProductDto>(dto, HttpStatus.OK);
		return response;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable long id, @RequestBody ProductDto dto)
	{
		ProductDto productDto = productService.updateProduct(id, dto);
		
		ResponseEntity<ProductDto> response = new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
		
		return response;
	}
}
