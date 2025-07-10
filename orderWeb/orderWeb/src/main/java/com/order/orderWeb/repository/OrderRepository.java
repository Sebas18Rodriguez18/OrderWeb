package com.order.orderWeb.repository;

import com.order.orderWeb.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> { }