package com.team1.spicycactus.bean;

public class DriverAndCar {
    private Driver driver;
    private Car car;

    public DriverAndCar() {
    }

    public DriverAndCar(Driver driver, Car car) {
        this.driver = driver;
        this.car = car;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
