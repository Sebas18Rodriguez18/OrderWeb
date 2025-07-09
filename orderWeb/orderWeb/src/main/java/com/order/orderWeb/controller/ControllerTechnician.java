package com.order.orderWeb.controller;

import com.order.orderWeb.model.Technician;
import com.order.orderWeb.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technician")
public class ControllerTechnician
{
    @Autowired
    private TechnicianRepository technicianRepository;

    @GetMapping
    public List<Technician> getAll()
    {
        return technicianRepository.findAll();
    }

    @GetMapping("/{id}")
    public Technician getByID(@PathVariable Long id)
    {
        return technicianRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Technician create(@RequestBody Technician technician)
    {
        return  technicianRepository.save(technician);
    }

    @PutMapping("/{id}")
    public Technician update(@PathVariable Long id, @RequestBody Technician technician)
    {
        technician.setId(id);
        return technicianRepository.save(technician);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id)
    {
        technicianRepository.deleteById(id);
    }
}
