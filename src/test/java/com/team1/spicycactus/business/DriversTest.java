package com.team1.spicycactus.business;

import com.team1.spicycactus.bean.Driver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DriversTest {

    @Autowired
    Drivers drivers;

    @Test
    void driverExists() {
        assertTrue(drivers.driverExists(1));
    }

    @Test
    void testDriverExists() {
        Driver driver = new Driver();
        driver.setDriver_id(1);
        assertTrue(drivers.driverExists(driver));
    }
}