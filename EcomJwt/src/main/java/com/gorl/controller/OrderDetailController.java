package com.gorl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gorl.dto.request.OrderDto;
import com.gorl.service.OrderDetailService;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("http://localhost:4200")
public class OrderDetailController {

	@Autowired
	private OrderDetailService orderDetailService;
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	@PostMapping("/placeorder")
	public ResponseEntity<String> placeOrder(@RequestBody OrderDto orderDto)
	{
		String response = orderDetailService.placeOrder(orderDto);
		
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(response, HttpStatus.OK);
		
		return responseEntity;
	}
}
