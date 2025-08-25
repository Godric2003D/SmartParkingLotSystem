package com.smartparking.repository;

import com.smartparking.entity.ParkingTransaction;
import com.smartparking.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingTransactionRepository extends JpaRepository<ParkingTransaction, Long> {

    // Find active transaction for a vehicle
    Optional<ParkingTransaction> findByVehicleAndCheckOutTimeIsNull(Vehicle vehicle);
}
