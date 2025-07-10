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

import com.order.orderWeb.model.Observation;
import com.order.orderWeb.repository.ObservationRepository;

@RestController
@RequestMapping("/observation")
public class ControllerObservation {

    @Autowired
    private ObservationRepository observationRepository;

    @RequestMapping
    public List<Observation>getAll(){
        return observationRepository.findAll();
    }

    @RequestMapping("/{id}")
    public Observation getById(@PathVariable Long id) {
        return observationRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Observation create (@RequestBody Observation observation) {
        return observationRepository.save(observation);
    }

    @PutMapping("/{id}")
    public Observation update(@PathVariable Long id, @RequestBody Observation observation) {
        observation.setId(id);
        return observationRepository.save(observation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        observationRepository.deleteById(id);
    }
}
