package com.team1.spicycactus.services;

import com.team1.spicycactus.bean.Car;
import com.team1.spicycactus.bean.Driver;
import com.team1.spicycactus.bean.DriverAndCar;
import com.team1.spicycactus.business.Drivers;
import com.team1.spicycactus.dao.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/drivers")
public class WebLayer {

    @Autowired
    DriverRepo driverRepo;

    Drivers driverCheck = new Drivers();

    //Create Driver
    @PostMapping("/")
    public ResponseEntity apiCreateDriver(@RequestParam(name = "geoCoordinate", required = true) String geoCoordinate, @RequestParam(name = "carId", required = true) int carId, @RequestParam(name = "licenseNumber", required = true) long licenseNumber){
        Driver newDriver = new Driver(geoCoordinate, LocalDateTime.now(), true, carId, licenseNumber);

        for(Driver currentDriver : driverRepo.findAll()) {
            if (currentDriver.getDrivers_license_number() == licenseNumber) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Driver Already Exists");
            }
        }
        driverRepo.save(newDriver);
        return ResponseEntity.status(HttpStatus.OK).body(newDriver);
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

        if(driverCheck.driverExists(driverRepo, driverId)){
            return ResponseEntity.status(HttpStatus.OK).body(driverRepo.findById(driverId));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Driver Not Present In Fleet");
        }
    }

//    Get Driver Car by Driver ID
    @GetMapping("/selectcar/{driverId}")
    public ResponseEntity apiGetCar(@PathVariable(name = "driverId") int driverId){
        List<Car> carList = mockAPICars();

        if(driverCheck.driverExists(driverRepo, driverId)){
            Driver currentDriver = driverRepo.findById(driverId).orElse(null);

            for(Car currentCar : carList){
                if((currentDriver != null ? currentDriver.getCar_id() : 0) == currentCar.getCar_id()){
                    DriverAndCar newDriverAndCar = new DriverAndCar(currentDriver, currentCar);
                    return ResponseEntity.status(HttpStatus.OK).body(newDriverAndCar);
                }
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Driver Does Not Have A Car Registered");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Driver Does Not Exist");
        }
    }

    //Get Automatic Cars
    @GetMapping("/criteria/automaticElectric")
    public ResponseEntity apiGetElectricCars(){
        List<Car> carList = new ArrayList<>();
        for(Car currentCar : mockAPICars()){
            if(currentCar.isTransmission()){
                carList.add(currentCar);
            }
        }
        if(carList.size() == 0){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No Electric/Automatic Cars");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(carList);
        }
    }

    @GetMapping("/deselect/{driverId}")
    public ResponseEntity apiDeslectCar(@PathVariable(name = "driverId") int driverId){

        for(Driver currentDriver : driverRepo.findAll()) {
            if (currentDriver.getDriver_id() == driverId) {
                currentDriver.setCar_id(0);
                driverRepo.save(currentDriver);
                return ResponseEntity.status(HttpStatus.OK).body(currentDriver);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Driver Does Not Exist");
    }

    @PostMapping("/assign/")
    public ResponseEntity apiSelectCar(@RequestParam(name = "driverId", required = true) int driverId, @RequestParam(name = "carId", required = true) int carId){

        for(Driver currentDriver : driverRepo.findAll()) {
            if (currentDriver.getDriver_id() == driverId) {
                currentDriver.setCar_id(carId);
                driverRepo.save(currentDriver);
                return ResponseEntity.status(HttpStatus.OK).body(currentDriver);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Driver Does Not Exist");
    }

    //----------------------------------- MOCK CARD

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