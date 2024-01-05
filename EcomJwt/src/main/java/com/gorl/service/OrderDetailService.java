package com.gorl.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.gorl.dto.request.OrderDto;

public interface OrderDetailService {
	
	String placeOrder(OrderDto orderDto);

}
