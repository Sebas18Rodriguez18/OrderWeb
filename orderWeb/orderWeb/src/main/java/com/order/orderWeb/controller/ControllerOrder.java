package com.order.orderWeb.controller;

import com.order.orderWeb.model.Order;
import com.order.orderWeb.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class ControllerOrder {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping
    public List<Order> GetAllActivities(){ return orderRepository.findAll();}

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id){return orderRepository.findById(id).orElse(null);}

    @PostMapping
    public Order create(@RequestBody Order order){return orderRepository.save(order);}

    @PutMapping
    public Order update(@PathVariable Long id, @RequestBody Order order){
        order.setId(id);
        return orderRepository.save(order);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id){orderRepository.deleteById(id);}
}
