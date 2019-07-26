package com.team1.spicycactus.services;

import com.team1.spicycactus.bean.Car;
import com.team1.spicycactus.bean.Driver;
import com.team1.spicycactus.dao.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1/drivers")
public class WebLayer {

    @Autowired
    DriverRepo driverRepo;

    //Create Driver
    @PostMapping("/")
    public ResponseEntity apiCreateDriver(@RequestParam(name = "geocoordinate", required = true) String geocoordinate, @RequestParam(name = "carId", required = true) int carId){
        Driver newDriver = new Driver(geocoordinate, LocalDateTime.now(), true, carId);

        if(newDriver.getGeo_coordinate().equals(newDriver.getGeo_coordinate())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        else {
            driverRepo.save(newDriver);
            return ResponseEntity.status(HttpStatus.OK).body(newDriver);
        }
    }

    //Get Drivers
    @GetMapping("/")
    public ResponseEntity apiGetAllDrivers(){
//        return driverRepo.findAll();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
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

//    //Get Driver Car by Driver ID
//    @GetMapping("/selectcar/{driverId}")
//    public Car apiGetCar(@PathVariable(name = "driverId") int driverId){
//
//    }
//
//    //Get Automatic Cars
//    @GetMapping("/criteria/automaticElectric")
//    public List<Car> apiGetElectricCars(){
//
//    }

    public List<Car> mockAPICars(){
        List<Car> carList = List.of(
            new Car(1, "Cool Car Model", "Rainbow", "123456", 5, 4, "Big", true, "Automatic", "BMW"),
            new Car(2, "Dumb Car Model", "Green", "123456", 5, 4, "Big", false, "Automatic", "BMW"),
            new Car(3, "Nice Car Model", "Blue", "123456", 5, 4, "Big", true, "Automatic", "BMW"),
            new Car(4, "Bad Car Model", "Red", "123456", 5, 4, "Big", false, "Automatic", "BMW")
        );
        return carList;
    }

}