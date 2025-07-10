package com.order.orderWeb.controller;

import com.order.orderWeb.model.Role;
import com.order.orderWeb.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class ControllerRole {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Role getById(@PathVariable Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Role create(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    @PutMapping("/{id}")
    public Role update(@PathVariable Long id, @RequestBody Role role) {
        role.setId(id);
        return roleRepository.save(role);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roleRepository.deleteById(id);
    }
}
