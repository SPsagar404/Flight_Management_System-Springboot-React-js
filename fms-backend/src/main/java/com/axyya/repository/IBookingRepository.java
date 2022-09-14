package com.axyya.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axyya.model.Booking;


public interface IBookingRepository extends JpaRepository<Booking,Long> {
	
	List<Booking> findAllByUserId(Long userId);
}
