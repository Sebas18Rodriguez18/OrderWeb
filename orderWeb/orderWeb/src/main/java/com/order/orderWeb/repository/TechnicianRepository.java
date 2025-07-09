package com.order.orderWeb.repository;

import com.order.orderWeb.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technician, Long>
{

}
