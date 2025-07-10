package com.order.orderWeb.repository;

import com.order.orderWeb.model.TypeActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeActivityRepository extends JpaRepository<TypeActivity, Long> {
}
