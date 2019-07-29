package com.team1.spicycactus.services;

import com.team1.spicycactus.bean.Car;
import com.team1.spicycactus.bean.Driver;
import com.team1.spicycactus.bean.DriverAndCar;
import io.swagger.models.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.AssertTrue;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebLayerTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void apiCreateDriver() {
        RestTemplate rTemp = new RestTemplate();
        ResponseEntity<Driver> response = rTemp.exchange(
                "http://localhost:4402/v1/drivers/?geoCoordinate=1010,1010&carId=4&licenseNumber=573822",
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<>() {});
        Driver testDriver = response.getBody();


        Assert.assertTrue(testDriver.getDrivers_license_number() == 573822);
        Assert.assertTrue(true);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);

    }

    @org.junit.jupiter.api.Test
    void apiGetAllDrivers() {
        RestTemplate rTemp = new RestTemplate();
        ResponseEntity<List<Driver>> response = rTemp.exchange(
                "http://localhost:4402/v1/drivers/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        List<Driver> testDriverList = response.getBody();

        Assert.assertFalse(response.getBody().equals("No Drivers Present In Fleet"));
        Assert.assertTrue(response.getBody().size() == testDriverList.size());
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);

    }

    @org.junit.jupiter.api.Test
    void apiGetDriver() {
        RestTemplate rTemp = new RestTemplate();
        ResponseEntity<Driver> response = rTemp.exchange(
                "http://localhost:4402/v1/drivers/1",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
        Driver testDriver = response.getBody();

        Assert.assertTrue(testDriver.getDriver_id() == 1);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);

    }

    @org.junit.jupiter.api.Test
    void apiGetCar() {
        RestTemplate rTemp = new RestTemplate();
        ResponseEntity<DriverAndCar> response = rTemp.exchange(
                "http://localhost:4402/v1/drivers/selectcar/1",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
        DriverAndCar testDriver = response.getBody();

        Assert.assertTrue(testDriver.getDriver().getDriver_id() == 1);
        Assert.assertTrue(testDriver.getDriver().getCar_id() == testDriver.getCar().getCar_id());
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);

    }

    @org.junit.jupiter.api.Test
    void apiGetElectricCars() {
        RestTemplate rTemp = new RestTemplate();
        ResponseEntity<List<Car>> response = rTemp.exchange(
                "http://localhost:4402/v1/drivers/criteria/automaticElectric",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
        List<Car> testDriver = response.getBody();

        Assert.assertTrue(testDriver.size() == 2);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
    }
}
