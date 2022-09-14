package com.axyya.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


import lombok.Data;

@Entity
@Data
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookingId;

	private Boolean checkedIn;
	private LocalDate booking_date;

	@OneToOne
	private Passenger passenger;
  
	@OneToOne
	private Flight flight;

	private long userId;
}
