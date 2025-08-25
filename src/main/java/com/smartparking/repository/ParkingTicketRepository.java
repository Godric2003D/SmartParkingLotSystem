package com.smartparking.repository;

import com.smartparking.entity.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
}
