package com.smartparking.service;

import com.smartparking.enums.VehicleType;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;

@Service
public class FeeCalculatorService {

    private static final Map<VehicleType, Double> HOURLY_RATE = new EnumMap<>(VehicleType.class);

    static {
        HOURLY_RATE.put(VehicleType.MOTORCYCLE, 20.0);
        HOURLY_RATE.put(VehicleType.CAR,        40.0);
        HOURLY_RATE.put(VehicleType.BUS,        80.0);
    }


    public double calculateFee(VehicleType type, LocalDateTime entry, LocalDateTime exit) {
        if (exit.isBefore(entry)) {
            throw new IllegalArgumentException("Exit time cannot be before entry time");
        }

        long minutes = Duration.between(entry, exit).toMinutes();


        double hours = Math.ceil(minutes / 60.0);


        hours = Math.max(1, hours);


        double rate = HOURLY_RATE.getOrDefault(type, 40.0);

        return hours * rate;
    }
}
