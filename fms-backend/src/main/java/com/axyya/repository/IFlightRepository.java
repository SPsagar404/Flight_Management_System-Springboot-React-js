package com.axyya.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axyya.model.Flight;

public interface IFlightRepository extends JpaRepository<Flight,Long> {

	@Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity ")
    List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity") String to);
}
