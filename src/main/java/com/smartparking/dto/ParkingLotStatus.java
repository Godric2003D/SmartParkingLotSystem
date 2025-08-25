package com.smartparking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ParkingLotStatus {
    private int totalSpots;
    private int availableSpots;
    private int occupiedSpots;
    private Map<String, Integer> availableSpotsByType;
}
