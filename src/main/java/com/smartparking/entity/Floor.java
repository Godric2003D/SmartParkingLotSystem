package com.smartparking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "floors")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Floor number (0,1,2,...)
    @Column(nullable = false, unique = true)
    private int number;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingSpot> spots = new ArrayList<>();

    public Floor(int number) { this.number = number; }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
        spot.setFloor(this);
    }
}
