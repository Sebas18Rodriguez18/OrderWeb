package com.order.orderWeb.model;


import jakarta.persistence.*;


@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer hours;

    @ManyToOne
    @JoinColumn(name="technician_id")
    private Technician technician;
    @ManyToOne
    @JoinColumn(name="type_activity_id")
    private TypeActivity type_activity;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeActivity getType_activity() {
        return type_activity;
    }

    public void setType_activity(TypeActivity type_activity) {
        this.type_activity = type_activity;
    }
}
