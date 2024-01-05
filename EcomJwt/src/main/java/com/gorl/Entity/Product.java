package com.gorl.Entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(name = "product",
		uniqueConstraints = {@UniqueConstraint(name="name_uniqueue", columnNames = "name")}
	   )
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	private int stockQuantity;
	
	private double price;
	
	private int discountPercent;
	
    private String color;
    
    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private ProductCategory productCategory;
    
    private String imageUrl;

}
