package com.gorl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorl.Entity.OrderDetail;
import com.gorl.Entity.Product;
import com.gorl.Entity.User;
import com.gorl.dto.request.OrderDto;
import com.gorl.dto.request.OrderProductQuantityDto;
import com.gorl.exception.ResourcenotFoundException;
import com.gorl.repo.OrderDetailRepo;
import com.gorl.repo.ProductRepo;
import com.gorl.repo.UserRepo;
import com.gorl.security.JwtAuthenticationFilter;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	private static final String ORDER_PLACED = "Order Placed";
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private OrderDetailRepo orderDetailRepo;
	
	@Override
	public String placeOrder(OrderDto orderDto) {
		
		List<OrderProductQuantityDto> orderProductQuantityDtos = orderDto.getProductQuantityDtos();
		
		for(OrderProductQuantityDto temp : orderProductQuantityDtos)
		{
			OrderDetail detail = new OrderDetail();
			detail.setBuyerName(orderDto.getBuyerName());
			detail.setBuyerAddr(orderDto.getBuyerAddr());
			detail.setBuyerMobNo(orderDto.getBuyerMobNo());
			detail.setBuyerAltMobNo(orderDto.getBuyerAltMobNo());
			detail.setOrderStatus(ORDER_PLACED);
			
			Product productEntity = productRepo.findById(temp.getProductId())
					.orElseThrow(()-> new ResourcenotFoundException("Product", "id", ""+temp.getProductId()));
			
			detail.setProduct(productEntity);
			detail.setQuantity(temp.getQuantity());
			detail.setOrderAmount(temp.getQuantity() * productEntity.getPrice());
			
			User user = userRepo.findByEmail(JwtAuthenticationFilter.CURRENT_USER).get();
			detail.setUser(user);
			
			orderDetailRepo.save(detail);
		}
		return "Order Placed";
	}
	
}
