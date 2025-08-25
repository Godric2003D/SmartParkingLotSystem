package com.smartparking.config;

import com.smartparking.entity.Floor;
import com.smartparking.entity.ParkingSpot;
import com.smartparking.enums.SpotSize;
import com.smartparking.repository.FloorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final FloorRepository floorRepository;

    @Override
    public void run(String... args) {
        if (floorRepository.count() > 0) return;

        for (int f = 0; f < 2; f++) {
            Floor floor = new Floor(f);
            int spotNum = 1;

            for (int i = 0; i < 6; i++) floor.addSpot(ParkingSpot.of(spotNum++, SpotSize.SMALL));
            for (int i = 0; i < 10; i++) floor.addSpot(ParkingSpot.of(spotNum++, SpotSize.MEDIUM));
            for (int i = 0; i < 4; i++) floor.addSpot(ParkingSpot.of(spotNum++, SpotSize.LARGE));

            floorRepository.save(floor);
        }
    }
}
