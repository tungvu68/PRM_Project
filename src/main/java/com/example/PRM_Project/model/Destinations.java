package com.example.PRM_Project.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="destinations")
public class Destinations {
    @Id
    private int destination_id;

    private String name;
    private String address;
    private String description;
    private String phone_number;
    private String category;
    private Date created_at;


    @ManyToOne
    @JoinColumn(name = "destination_trip_id", nullable = false)
    private DestinationTrip destinationTrip;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DestinationRate> rates;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Destinations() {}
    public Destinations(int destination_id, String name, String address, String description, String phone_number, String category,Date created_at) {
        this.destination_id = destination_id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.category = category;
        this.phone_number = phone_number;
        this.created_at = created_at;
    }

    public int getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(int destination_id) {
        this.destination_id = destination_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public List<DestinationRate> getRates() {
        return rates;
    }

    public void setRates(List<DestinationRate> rates) {
        this.rates = rates;
    }
}
