package com.team1.spicycactus.business;

import com.team1.spicycactus.bean.Driver;
import com.team1.spicycactus.dao.DriverRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DriversTest {

    @Autowired
    Drivers drivers;

    @Autowired
    DriverRepo driverRepo;

    @Test
    void driverExists() {
        assertTrue(drivers.driverExists(driverRepo, 1));
    }

    @Test
    void testDriverExists() {
        Driver driver = new Driver();
        driver.setDriver_id(1);
        assertTrue(drivers.driverExists(driverRepo, driver));
    }

    @Test
    void testDriverOnline() {
//        assertFalse(drivers.driverOnline(driverRepo, 2));
//        assertTrue(drivers.driverOnline(driverRepo, 1));
    }
}