package com.axyya.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;

@Entity
@Data
public class Flight{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long flightId;
	private String flightNumber;
	private String operatingAirlines;
	private String departureCity;
	private String arrivalCity;
	private LocalDate dateOfDeparture;
	
	
	
	public Flight(String operatingAirlines, String departureCity, String arrivalCity,
			LocalDate dateOfDeparture) {
		super();
		this.operatingAirlines = operatingAirlines;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.dateOfDeparture = dateOfDeparture;
	}



	public Flight() {
		super();
	}
	
	
	
  

}