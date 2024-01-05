package com.gorl.dto.request;

import java.util.Set;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	private Long id;

	@NotEmpty
	@Size(min = 4, message = "product name must have minimum 4 chars")
	private String name;

	@NotEmpty
	@Size(min = 10, message = "product description must have minimum 10 chars")
	private String description;

	@Min(10)
	private int stockQuantity;
	
	@Min(10)
	private double price;

	@Min(1)
	private int discountPercent;

	@NotEmpty
	@Size(min = 3, message = "product color must have minimum 3 chars")
	private String color;

	@NotEmpty
	@Size(min = 3, message = "product category must have minimum 4 chars")
	private String productCategory;
	
	@NotEmpty
	private String imageUrl;
}
