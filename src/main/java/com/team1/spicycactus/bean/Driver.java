package com.team1.spicycactus.bean;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driver_id;
    private String geo_coordinate;
    private LocalDateTime date_coordinate_updated;
    private boolean online_status;
    private int car_id;
    private long drivers_license_number;

    public Driver() {
    }

    public Driver(String geo_coordinate, LocalDateTime date_coordinate_updated, boolean online_status, int car_id, long drivers_license_number) {
        this.geo_coordinate = geo_coordinate;
        this.date_coordinate_updated = date_coordinate_updated;
        this.online_status = online_status;
        this.car_id = car_id;
        this.drivers_license_number = drivers_license_number;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getGeo_coordinate() {
        return geo_coordinate;
    }

    public void setGeo_coordinate(String geo_coordinate) {
        this.geo_coordinate = geo_coordinate;
    }

    public LocalDateTime getDate_coordinate_updated() {
        return date_coordinate_updated;
    }

    public void setDate_coordinate_updated(LocalDateTime date_coordinate_updated) {
        this.date_coordinate_updated = date_coordinate_updated;
    }

    public boolean isOnline_status() {
        return online_status;
    }

    public void setOnline_status(boolean online_status) {
        this.online_status = online_status;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }
}
