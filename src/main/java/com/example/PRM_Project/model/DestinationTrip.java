package com.example.PRM_Project.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="destination_trip")
public class DestinationTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int destination_trip_id;

    private int user_id;
    private Date save_time;

    @OneToMany(mappedBy = "destinationTrip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trip> trips;

    @OneToMany(mappedBy = "destinationTrip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Destinations> destinations;

    public DestinationTrip(int destination_trip_id, int user_id, Date save_time, List<Trip> trips, List<Destinations> destinations) {
        this.destination_trip_id = destination_trip_id;
        this.user_id = user_id;
        this.save_time = save_time;
        this.trips = trips;
        this.destinations = destinations;
    }

    public int getDestination_trip_id() {
        return destination_trip_id;
    }

    public void setDestination_trip_id(int destination_trip_id) {
        this.destination_trip_id = destination_trip_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getSave_time() {
        return save_time;
    }

    public void setSave_time(Date save_time) {
        this.save_time = save_time;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Destinations> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destinations> destinations) {
        this.destinations = destinations;
    }
}
