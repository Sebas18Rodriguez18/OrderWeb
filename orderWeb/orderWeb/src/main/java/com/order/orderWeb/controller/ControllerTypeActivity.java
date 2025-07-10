package com.order.orderWeb.controller;

import com.order.orderWeb.model.TypeActivity;
import com.order.orderWeb.repository.TypeActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/type_activity")
public class ControllerTypeActivity {

    @Autowired
    private TypeActivityRepository typeActivityRepository;

    @GetMapping("/type_activity")
    public String listarTypeActivity(Model model) {
        model.addAttribute("type_activity", typeActivityRepository.findAll());
        return "type_activity";
    }

    @GetMapping("/viewTA/form")
    public String formularioCrear(Model model) {
        model.addAttribute("typeActivity", new TypeActivity());
        return "form_type_activity";
    }

    @PostMapping("/viewTA/save")
    public String guardar(@ModelAttribute TypeActivity typeActivity) {
        typeActivityRepository.save(typeActivity);
        return "redirect:/view/type_activity";
    }

    @GetMapping("/viewTA/edit/{id}")
    public String editar(@PathVariable Long id, Model model) {
        TypeActivity tipo = typeActivityRepository.findById(id).orElseThrow();
        model.addAttribute("typeActivity", tipo);
        return "form_type_activity";
    }

    @PostMapping("/viewTA/delete/{id}")
    public String eliminar(@PathVariable Long id) {
        typeActivityRepository.deleteById(id);
        return "redirect:/view/type_activity";
    }
}
