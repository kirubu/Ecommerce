package com.gorl.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorl.Entity.OrderDetail;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long>{

}
