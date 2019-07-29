package com.team1.spicycactus.business;

import com.team1.spicycactus.bean.Car;
import com.team1.spicycactus.bean.Driver;
import com.team1.spicycactus.bean.DriverAndCar;
import com.team1.spicycactus.dao.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class Drivers {

    /**
     * Checks for driver in db by id
     * @param id - driverID
     * @return - true or false
     */
    public boolean driverExists(DriverRepo driverRepo, int id){
        return driverRepo.existsById(id);
    }

    /**
     * Checks for driver based on object
     * @param driver - Driver object
     * @return true or false
     */
    public boolean driverExists(DriverRepo driverRepo, Driver driver){
        return driverRepo.findById(driver.getDriver_id()).isPresent();
    }

    /**
     * Checks Drivers Online Status
     * @param id - Driver_ID
     * @return true or false
     */
    public boolean driverOnline(DriverRepo driverRepo, int id, Boolean online) {
        Driver driver = driverRepo.findById(id).orElse(null);
        driver.setOnline_status(online);
        driverRepo.save(driver);
        return true;
    }

}
