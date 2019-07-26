package com.team1.spicycactus.dao;

import com.team1.spicycactus.bean.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<Driver, Integer> {
}
