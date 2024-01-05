package com.gorl.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorl.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
