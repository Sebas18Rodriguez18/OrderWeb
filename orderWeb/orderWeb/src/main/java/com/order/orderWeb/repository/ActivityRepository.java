package com.order.orderWeb.repository;

import com.order.orderWeb.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Integer> { }
