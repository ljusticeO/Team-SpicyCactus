package com.team1.spicycactus.services;

import com.team1.spicycactus.bean.Car;
import com.team1.spicycactus.bean.Driver;
import com.team1.spicycactus.dao.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/drivers")
public class WebLayer {

    @Autowired
    DriverRepo driverRepo;

    //Create Driver
    @PostMapping("/")
    public Driver apiCreateDriver(@RequestParam(name = "geocoordinate", required = true) String geocoordinate, @RequestParam(name = "carId", required = true) int carId){
        Driver newDriver = new Driver(geocoordinate, LocalDateTime.now(), true, carId);
        driverRepo.save(newDriver);
        return newDriver;
    }

    //Get Drivers
    @GetMapping("/")
    public List<Driver> apiGetAllDrivers(){
        return driverRepo.findAll();
    }

    //Get Driver By ID
    @GetMapping("/{driverId}")
    public Driver apiGetDriver(@PathVariable(name = "driverId") int driverId){
        for(Driver currentDriver : driverRepo.findAll()){
            if(currentDriver.getDriver_id() == driverId){
                return currentDriver;
            }
        }
        return null;
    }

    //Get Driver Car by Driver ID
    @GetMapping("/selectcar/{driverId}")
    public Car apiGetCar(@PathVariable(name = "driverId") int driverId){

    }

    //Get Automatic Cars
    @GetMapping("/criteria/automaticElectric")
    public List<Car> apiGetElectricCars(){

    }

}
