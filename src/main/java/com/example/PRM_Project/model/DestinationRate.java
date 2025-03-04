package com.example.PRM_Project.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="destinations_rate")
public class DestinationRate {

    @Id
    private int destinations_rate_id;

    private int rate;
    private Date created_date;

    public DestinationRate(int destinations_rate_id, int rate, Date created_date, Destinations destination) {
        this.destinations_rate_id = destinations_rate_id;
        this.rate = rate;
        this.created_date = created_date;
        this.destination = destination;
    }

    public DestinationRate() {
    }

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destinations destination;

    public int getDestinationsRateId() {
        return destinations_rate_id;
    }

    public void setDestinationsRateId(int destinations_rate_id) {
        this.destinations_rate_id = destinations_rate_id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Date getCreatedDate() {
        return created_date;
    }

    public void setCreatedDate(Date created_date) {
        this.created_date = created_date;
    }

    public Destinations getDestination() {
        return destination;
    }

    public void setDestination(Destinations destination) {
        this.destination = destination;
    }
}
