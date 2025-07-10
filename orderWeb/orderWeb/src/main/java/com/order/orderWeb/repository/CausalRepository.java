package com.order.orderWeb.repository;

import com.order.orderWeb.model.Causal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CausalRepository extends JpaRepository<Causal, Long> {}
