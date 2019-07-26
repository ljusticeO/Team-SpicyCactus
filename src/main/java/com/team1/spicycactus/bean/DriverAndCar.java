package com.team1.spicycactus.bean;

/**
 * DriverAndCar Object containing both the Driver and Car objects
 */
public class DriverAndCar {
    private Driver driver;
    private Car car;

    /**
     * Constructors
     */
    public DriverAndCar() {
    }

    public DriverAndCar(Driver driver, Car car) {
        this.driver = driver;
        this.car = car;
    }

    /**
     * getters
     */
    public Driver getDriver() {
        return driver;
    }

    public Car getCar() {
        return car;
    }

    /**
     * setters
     */
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
