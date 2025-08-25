package com.smartparking.service;

import com.smartparking.dto.AvailabilityResponse;
import com.smartparking.entity.ParkingSpot;
import com.smartparking.entity.ParkingTransaction;
import com.smartparking.entity.Vehicle;
import com.smartparking.enums.SpotSize;
import com.smartparking.enums.VehicleType;
import com.smartparking.repository.ParkingSpotRepository;
import com.smartparking.repository.ParkingTransactionRepository;
import com.smartparking.repository.VehicleRepository;
import com.smartparking.repository.FloorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingSpotRepository spotRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingTransactionRepository transactionRepository;
    private final FloorRepository floorRepository;

    public String checkInVehicle(String vehicleNumber, VehicleType type) {
        // Check if vehicle already exists in parking
        Optional<Vehicle> existingVehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        if (existingVehicle.isPresent()) {
            return "Vehicle is already parked.";
        }

        // Find an available spot for the vehicle type
        SpotSize spotSize = getSpotSizeForVehicle(type);
        Optional<ParkingSpot> availableSpot = spotRepository.findFirstBySizeAndOccupiedFalse(spotSize);
        if (availableSpot.isEmpty()) {
            return "No available spot for vehicle type: " + type;
        }


        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(vehicleNumber);
        vehicle.setType(type);
        vehicleRepository.save(vehicle);


        ParkingSpot spot = availableSpot.get();
        spot.setOccupied(true);
        spotRepository.save(spot);

        ParkingTransaction transaction = new ParkingTransaction();
        transaction.setVehicle(vehicle);
        transaction.setParkingSpot(spot);
        transaction.setCheckInTime(LocalDateTime.now());
        transaction.setParkingFee(0.0); // Corrected
        transactionRepository.save(transaction);

        return "Vehicle checked in at Spot ID: " + spot.getId();
    }


    public String checkOutVehicle(String vehicleNumber) {
        Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("Vehicle not found in the parking lot."));

        ParkingTransaction transaction = (ParkingTransaction) transactionRepository
                .findByVehicleAndCheckOutTimeIsNull(vehicle)
                .orElseThrow(() -> new RuntimeException("No active parking record found for this vehicle."));

        transaction.setCheckOutTime(LocalDateTime.now());
        double fee = calculateFee(transaction);
        transaction.setParkingFee(fee); // Corrected
        transactionRepository.save(transaction);

        ParkingSpot spot = transaction.getParkingSpot();
        spot.setOccupied(false);
        spotRepository.save(spot);

        return "Vehicle checked out. Fee: ₹" + fee;
    }


    private double calculateFee(ParkingTransaction transaction) {
        LocalDateTime checkIn = transaction.getCheckInTime();
        LocalDateTime checkOut = transaction.getCheckOutTime();
        long minutes = Duration.between(checkIn, checkOut).toMinutes();
        double hours = Math.ceil(minutes / 60.0);

        // Fee logic: Different rates for different vehicle types
        switch (transaction.getVehicle().getType()) {
            case MOTORCYCLE:
                return hours * 10; // ₹10 per hour
            case CAR:
                return hours * 20; // ₹20 per hour
            case BUS:
                return hours * 50; // ₹50 per hour
            default:
                return hours * 20;
        }
    }


    private SpotSize getSpotSizeForVehicle(VehicleType type) {
        return switch (type) {
            case MOTORCYCLE -> SpotSize.SMALL;
            case CAR -> SpotSize.MEDIUM;
            case BUS -> SpotSize.LARGE;
            default -> throw new IllegalArgumentException("Unknown vehicle type: " + type);
        };
    }


    public List<Vehicle> getAllCurrentVehicles() {
        return vehicleRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<AvailabilityResponse> getAvailability() {
        return floorRepository.findAll().stream().map(f -> {
            List<ParkingSpot> spots = spotRepository.findByFloor_Number(f.getNumber());
            long total = spots.size();
            long free = spots.stream().filter(s -> !s.isOccupied()).count();

            EnumMap<SpotSize, Long> freeBySize = new EnumMap<>(SpotSize.class);
            for (SpotSize size : SpotSize.values()) {
                freeBySize.put(size, spots.stream()
                        .filter(s -> !s.isOccupied() && s.getSize() == size)
                        .count());
            }
            return new AvailabilityResponse(f.getNumber(), total, free, freeBySize);
        }).collect(Collectors.toList());
    }
}
