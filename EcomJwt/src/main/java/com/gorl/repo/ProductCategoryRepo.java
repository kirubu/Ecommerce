package com.gorl.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorl.Entity.ProductCategory;


public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long>{

	Optional<ProductCategory> findByCategoryName(String categoryName);
}
