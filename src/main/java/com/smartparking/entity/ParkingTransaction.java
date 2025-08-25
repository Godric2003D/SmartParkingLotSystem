package com.smartparking.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_transactions", indexes = {
        @Index(name = "idx_check_in_time", columnList = "checkInTime")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "spot_id", nullable = false)
    private ParkingSpot parkingSpot;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    private Double parkingFee;
}
