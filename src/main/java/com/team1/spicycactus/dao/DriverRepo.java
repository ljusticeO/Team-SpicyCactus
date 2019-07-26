package com.team1.spicycactus.dao;

import com.team1.spicycactus.bean.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DriverRepo extends JpaRepository<Driver, Integer> {
    @Query(value = "SELECT d from Driver d WHERE d.drivers_license_number = :drivers_license_number", nativeQuery = false)
    Optional<Driver> findByLicenseNumber(String drivers_license_number);
}
