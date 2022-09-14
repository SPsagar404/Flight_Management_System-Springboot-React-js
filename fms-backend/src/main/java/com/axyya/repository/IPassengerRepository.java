package com.axyya.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.axyya.model.Passenger;

public interface IPassengerRepository extends JpaRepository<Passenger,Long> {
}
