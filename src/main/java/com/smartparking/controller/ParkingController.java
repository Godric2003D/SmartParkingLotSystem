package com.smartparking.controller;

import com.smartparking.dto.AvailabilityResponse;
import com.smartparking.enums.VehicleType;
import com.smartparking.service.ParkingService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    // Check-in (auto allocates a spot based on vehicle type/size rules)
    @PostMapping("/entry")
    public String vehicleEntry(@RequestParam @NotBlank String vehicleNumber,
                               @RequestParam VehicleType type) {
        return parkingService.checkInVehicle(vehicleNumber, type);
    }

    // Check-out (computes fee, frees spot)
    @PostMapping("/exit")
    public String vehicleExit(@RequestParam String vehicleNumber) {
        return parkingService.checkOutVehicle(vehicleNumber);
    }

    // Get all active vehicles (simplified version)
    @GetMapping("/active")
    public List<Map<String, Object>> getActiveVehicles() {
        return parkingService.getAllCurrentVehicles().stream().map(vehicle -> {
            Map<String, Object> info = new HashMap<>();
            info.put("id", vehicle.getId());
            info.put("vehicleNumber", vehicle.getVehicleNumber());
            info.put("vehicleType", vehicle.getType());
            return info;
        }).toList();
    }
    @GetMapping("/availability")
    public List<AvailabilityResponse> getAvailability() {
        return parkingService.getAvailability();
    }
}
