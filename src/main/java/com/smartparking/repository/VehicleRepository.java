package com.smartparking.repository;

import com.smartparking.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // Return Optional so orElseThrow() works in service
    Optional<Vehicle> findByVehicleNumber(String vehicleNumber);
}
