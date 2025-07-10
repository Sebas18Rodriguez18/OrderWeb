package com.order.orderWeb.controller;

import com.order.orderWeb.model.TypeActivity;
import com.order.orderWeb.repository.TypeActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ViewTypeActivity {
    @Autowired
    TypeActivityRepository typeActivityRepository;

    @GetMapping("/view/type_activity")
    public String list(Model model){
        model.addAttribute("type_activity", typeActivityRepository.findAll());
        return "type_activity";
    }

    @GetMapping("viewTA/form")
    public String form(Model model){
        model.addAttribute("type_activity", new TypeActivity());
        return "type_activity_form";
    }

    @PostMapping("viewTA/save")
    public String save(@ModelAttribute TypeActivity typeActivity, RedirectAttributes ra){
        typeActivityRepository.save(typeActivity);
        ra.addFlashAttribute("success", "Type Activity saved");
        return "redirect:/view/type_activity";
    }

    @GetMapping("/viewTA/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        TypeActivity typeActivity = typeActivityRepository.findById(id).orElse(null);
        model.addAttribute("type_activity", typeActivity);
        return "type_activity_form";
    }

    @PostMapping("/viewTA/delete/{id}")
    public String delete (@PathVariable Long id, RedirectAttributes ra){
        typeActivityRepository.deleteById(id);
        ra.addFlashAttribute("success", "Type Activity deleted");
        return "redirect:/view/type_activity";
    }
}