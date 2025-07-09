package com.order.orderWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.orderWeb.model.Causal;
import com.order.orderWeb.repository.CausalRepository;

@RestController
@RequestMapping("/causal")
public class ControllerCausal {

    @Autowired
    private CausalRepository causalRepository;

    @RequestMapping
    public List<Causal>getAll(){
        return causalRepository.findAll();
    }

    @RequestMapping("/{id}")
    public Causal getById(@PathVariable Long id) {
        return causalRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Causal create (@RequestBody Causal causal) {
        return causalRepository.save(causal);
    }

    @PutMapping("/{id}")
    public Causal update(@PathVariable Long id, @RequestBody Causal causal) {
        causal.setId(id);
        return causalRepository.save(causal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        causalRepository.deleteById(id);
    }
}
