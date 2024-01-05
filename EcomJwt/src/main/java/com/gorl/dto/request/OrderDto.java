package com.gorl.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

	private String buyerName;
	private String buyerAddr;
	private String buyerMobNo;
	private String buyerAltMobNo;
	private List<OrderProductQuantityDto> productQuantityDtos;
}
