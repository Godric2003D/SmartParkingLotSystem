package com.smartparking.repository;

import com.smartparking.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FloorRepository extends JpaRepository<Floor, Long> {
    boolean existsByNumber(int number);
    Floor findByNumber(int number);
}
