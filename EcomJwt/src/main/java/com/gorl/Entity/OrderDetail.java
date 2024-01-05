package com.gorl.Entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String buyerName;
	private String buyerAddr;
	private String buyerMobNo;
	private String buyerAltMobNo;
	private String orderStatus;
	private int quantity;
	private double orderAmount;
	
	@CreationTimestamp
	private Date orderDate;
	
	@OneToOne
	@JoinColumn(unique=false)
	private Product product;
	
	@OneToOne
	@JoinColumn(unique=false)
	private User user;
	
}
