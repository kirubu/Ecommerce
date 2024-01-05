package com.gorl.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {

	
	private Long id;
	
	@NotEmpty
	@Size(min = 4, message = "Title must have minimum 4 chars")
	private String categoryName;
}
