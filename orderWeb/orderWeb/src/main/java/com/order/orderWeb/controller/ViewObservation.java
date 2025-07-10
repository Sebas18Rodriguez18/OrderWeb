package com.order.orderWeb.controller;

import com.order.orderWeb.model.Observation;
import com.order.orderWeb.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view/observation")
public class ViewObservation {

    @Autowired
    private ObservationRepository observationRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("observations", observationRepository.findAll());
        return "observation";
    }

    @GetMapping("/form")
    public String formulario(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            Observation observation = observationRepository.findById(id).orElse(new Observation());
            model.addAttribute("observation", observation);
        } else {
            model.addAttribute("observation", new Observation());
        }
        return "observation_form";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute Observation observation) {
        observationRepository.save(observation);
        return "redirect:/view/observation";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Observation observation = observationRepository.findById(id).orElse(new Observation());
        model.addAttribute("observation", observation);
        return "observation_form";
    }

    @PostMapping("/delete/{id}")
    public String eliminar(@PathVariable Long id) {
        observationRepository.deleteById(id);
        return "redirect:/view/observation";
    }
}
