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
    public ResponseEntity apiCreateDriver(@RequestParam(name = "geoCoordinate", required = true) String geoCoordinate, @RequestParam(name = "carId", required = true) int carId){
        Driver newDriver = new Driver(geoCoordinate, LocalDateTime.now(), true, carId);

        if(newDriver.getGeo_coordinate().equals(newDriver.getGeo_coordinate())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Driver Already Exists");
        }
        else {
            driverRepo.save(newDriver);
            return ResponseEntity.status(HttpStatus.OK).body(newDriver);
        }
    }

    //Get Drivers
    @GetMapping("/")
    public ResponseEntity apiGetAllDrivers(){
        if(driverRepo.findAll().size() == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Drivers Present In Fleet");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(driverRepo.findAll());
        }
    }

    //Get Driver By ID
    @GetMapping("/{driverId}")
    public ResponseEntity apiGetDriver(@PathVariable(name = "driverId") int driverId){
        for(Driver currentDriver : driverRepo.findAll()){
            if(currentDriver.getDriver_id() == driverId){
                return ResponseEntity.status(HttpStatus.OK).body(currentDriver);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Driver Not Present In Fleet");
    }

    //Get Driver Car by Driver ID
    @GetMapping("/selectcar/{driverId}")
    public ResponseEntity apiGetCar(@PathVariable(name = "driverId") int driverId){
        List<Car> mockCarList = mockAPICars();
        if(mockCarList.size() == 0){
            return ResponseEntity.status(HttpStatus.OK).body(currentDriver);
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(mockCarList);
        }
    }

    //Get Automatic Cars
    @GetMapping("/criteria/automaticElectric")
    public List<Car> apiGetElectricCars(){
        return null;
    }

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