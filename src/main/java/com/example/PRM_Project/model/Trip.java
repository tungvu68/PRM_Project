package com.example.PRM_Project.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="trips")
public class Trip {

    @Id
    private int trip_id;
    private String type ;
    private String name;
    private Date created_date;
    private int created_by;

    @ManyToOne
    @JoinColumn(name = "destination_trip_id", nullable = false)
    private DestinationTrip destinationTrip;

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Trip(int trip_id, String type, String name, Date created_date, int created_by) {
        this.trip_id = trip_id;
        this.type = type;
        this.name = name;
        this.created_date = created_date;
        this.created_by = created_by;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }
}
