package com.order.orderWeb.controller;

import com.order.orderWeb.model.Role;
import com.order.orderWeb.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view/role")
public class ViewRole {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        return "role";
    }

    @GetMapping("/form")
    public String formulario(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            Role role = roleRepository.findById(id).orElse(new Role());
            model.addAttribute("role", role);
        } else {
            model.addAttribute("role", new Role());
        }
        return "role_form";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute Role role) {
        roleRepository.save(role);
        return "redirect:/view/role";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Role role = roleRepository.findById(id).orElse(new Role());
        model.addAttribute("role", role);
        return "role_form";
    }

    @PostMapping("/delete/{id}")
    public String eliminar(@PathVariable Long id) {
        roleRepository.deleteById(id);
        return "redirect:/view/role";
    }
}
