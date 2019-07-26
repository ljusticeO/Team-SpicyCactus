package com.team1.spicycactus.business;

import com.team1.spicycactus.bean.Driver;
import com.team1.spicycactus.dao.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class Drivers {

    @Autowired
    DriverRepo driverRepo;

    /**
     * Checks for driver in db by id
     * @param id - driverID
     * @return - true or false
     */
    public boolean driverExists(int id){
        return driverRepo.findById(id).isPresent();
    }

    /**
     * Checks for driver based on object
     * @param driver - Driver object
     * @return true or false
     */
    public boolean driverExists(Driver driver){
        return driverRepo.findById(driver.getDriver_id()).isPresent();
    }


}
