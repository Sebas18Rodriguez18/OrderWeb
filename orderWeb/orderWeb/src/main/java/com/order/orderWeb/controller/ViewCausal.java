package com.order.orderWeb.controller;

import com.order.orderWeb.model.Causal;
import com.order.orderWeb.repository.CausalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view/causal")
public class ViewCausal {

    @Autowired
    private CausalRepository causalRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("causales", causalRepository.findAll());
        return "causal";
    }

    @GetMapping("/form")
    public String formulario(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            Causal causal = causalRepository.findById(id).orElse(new Causal());
            model.addAttribute("causal", causal);
        } else {
            model.addAttribute("causal", new Causal());
        }
        return "causal_form";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute Causal causal) {
        causalRepository.save(causal);
        return "redirect:/view/causal";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Causal causal = causalRepository.findById(id).orElse(new Causal());
        model.addAttribute("causal", causal);
        return "causal_form";
    }

    @PostMapping("/delete/{id}")
    public String eliminar(@PathVariable Long id) {
        causalRepository.deleteById(id);
        return "redirect:/view/causal";
    }
}
