package com.order.orderWeb.controller;

import com.order.orderWeb.model.Activity;
import com.order.orderWeb.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ControllerActivity {
    @Autowired
    ActivityRepository activityRepository;

    @GetMapping
    public List<Activity> getAllActivities() { return activityRepository.findAll(); }

    @GetMapping("/{id}")
    public Activity getById(@PathVariable Integer id){return activityRepository.findById(id).orElse(null);}

    @PostMapping
    public Activity create(@RequestBody Activity activity) {return activityRepository.save(activity);}

    @PutMapping
    public Activity update(@PathVariable Long id, @RequestBody Activity activity)
    {
        activity.setId(id);
        return activityRepository.save(activity);
    }

    @DeleteMapping
    public void delete(@PathVariable Integer id) { activityRepository.deleteById(id); }
}
