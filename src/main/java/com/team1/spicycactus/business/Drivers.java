package com.team1.spicycactus.business;

import com.team1.spicycactus.bean.Driver;
import com.team1.spicycactus.dao.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class Drivers {

    @Autowired
    DriverRepo driverRepo;

    public boolean driverExists(int id){
        return driverRepo.findById(id).isPresent();
    }
    public boolean driverExists(Driver driver){
        return driverRepo.findById(driver.getDriver_id()).isPresent();
    }


}
