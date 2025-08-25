package com.smartparking.entity;

import com.smartparking.enums.SpotSize;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "spots", indexes = {
        @Index(name = "idx_spot_floor", columnList = "floor_id"),
        @Index(name = "idx_spot_size", columnList = "size"),
        @Index(name = "idx_spot_occupied", columnList = "occupied")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ParkingSpot {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Spot number within the floor
    @Column(nullable = false)
    private int number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpotSize size;

    @Column(nullable = false)
    private boolean occupied = false;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id", nullable = false)
    private Floor floor;


    @Version
    private Long version;

    public static ParkingSpot of(Integer number, SpotSize size) {
        ParkingSpot s = new ParkingSpot();
        s.number = number;
        s.size = size;
        s.occupied = false;
        return s;
    }
}
