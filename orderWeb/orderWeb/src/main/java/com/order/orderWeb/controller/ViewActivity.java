package com.order.orderWeb.controller;

import com.order.orderWeb.model.Activity;
import com.order.orderWeb.repository.ActivityRepository;
import com.order.orderWeb.repository.TechnicianRepository;
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

public class ViewActivity {
    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    TechnicianRepository technicianRepository;

    @Autowired
    TypeActivityRepository typeActivityRepository;

    @GetMapping("/view/activity")
    public String list(Model model) {
        model.addAttribute("activity", activityRepository.findAll());
        return "activity";
    }

    @GetMapping("viewA/form")
    public String form(Model model){
        model.addAttribute("activity", new Activity());
        model.addAttribute("technician", technicianRepository.findAll());
        model.addAttribute("type_activity", typeActivityRepository.findAll());
        return "activity_form";
    }

    @PostMapping("viewA/save")
    public String save(@ModelAttribute("activity") Activity activity, RedirectAttributes ra ){
        activityRepository.save(activity);
        ra.addAttribute("success", "Activity saved");
        return "redirect:/view/activity";
    }

    @GetMapping("/viewA/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Activity activity = activityRepository.findById(id).orElse(null);
        model.addAttribute("activity", activity);
        return "activity_form";
    }

    @PostMapping("/viewA/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra ){
        activityRepository.deleteById(id);
        ra.addAttribute("success", "Activity deleted");
        return "redirect:/view/activity";
    }
}
