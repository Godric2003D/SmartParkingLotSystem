package com.smartparking.entity;

import com.smartparking.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets", indexes = {
        @Index(name = "idx_ticket_active", columnList = "active"),
        @Index(name = "idx_ticket_vehicle", columnList = "vehicleNumber")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ParkingTicket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String vehicleNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", nullable = false)
    private ParkingSpot spot;

    @Column(nullable = false)
    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    @Column
    private Double fee;

    @Column(nullable = false)
    private boolean active = true;

    @Version
    private Long version;
}
