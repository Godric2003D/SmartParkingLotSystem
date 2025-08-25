package com.smartparking.dto;

import com.smartparking.enums.VehicleType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TicketResponse {
    private Long ticketId;
    private String vehicleNumber;
    private VehicleType vehicleType;
    private Long spotId;
    private Integer floorNumber;
    private Integer spotNumber;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Double fee;
    private boolean active;
}
