package com.order.orderWeb.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String city;

    @Column(name = "legalization_date")
    private LocalDate legalizationDate;

    @ManyToOne
    @JoinColumn(name = "causal_id")
    private Causal causal;

    @ManyToOne
    @JoinColumn(name = "observation_id")
    private Observation observation;

    // Getters y Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public LocalDate getLegalizationDate() { return legalizationDate; }
    public void setLegalizationDate(LocalDate legalizationDate) { this.legalizationDate = legalizationDate; }

    public Causal getCausal() { return causal; }
    public void setCausal(Causal causal) { this.causal = causal; }

    public Observation getObservation() { return observation; }
    public void setObservation(Observation observation) { this.observation = observation; }
}
