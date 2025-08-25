package com.smartparking.dto;

import com.smartparking.enums.SpotSize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.EnumMap;

@Data
@AllArgsConstructor
public class AvailabilityResponse {
    private int floorNumber;
    private long total;
    private long free;
    private EnumMap<SpotSize, Long> freeBySize;
}
