package com.smartparking.repository;

import com.smartparking.entity.ParkingSpot;
import com.smartparking.enums.SpotSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {


    Optional<ParkingSpot> findFirstBySizeAndOccupiedFalse(SpotSize size);


    boolean existsByIdAndOccupiedFalse(Long id);


    long countByFloor_NumberAndSizeAndOccupiedFalse(int floorNumber, SpotSize size);


    long countBySizeAndOccupiedFalse(SpotSize size);


    List<ParkingSpot> findByFloor_Number(int floorNumber);
}
