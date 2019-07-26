package com.team1.spicycactus;

import com.team1.spicycactus.bean.Driver;
import com.team1.spicycactus.dao.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
@EnableDiscoveryClient
public class SpicycactusApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpicycactusApplication.class, args);

    }

//    @Component
//    class DemoCommandLineRunner implements CommandLineRunner{
//
//        @Autowired
//        private DriverRepo driverRepo;
//
//        @Override
//        public void run(String... args) throws Exception {
//            Driver driver = new Driver("11911,29292", LocalDateTime.now(), true, 1, 22301);
//            Driver driver1 = new Driver("11911,29292", LocalDateTime.now(), true, 2,88171);
//            Driver driver2 = new Driver("11911,29292", LocalDateTime.now(), true, 3, 27271);
//
//            driverRepo.save(driver);
//            driverRepo.save(driver1);
//            driverRepo.save(driver2);
//
//        }
//    }
}
