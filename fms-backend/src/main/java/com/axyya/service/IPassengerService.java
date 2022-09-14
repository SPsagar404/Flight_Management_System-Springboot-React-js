package com.axyya.service;

import java.util.List;

import com.axyya.model.Passenger;

public interface IPassengerService {
	
	public List<Passenger> getAllPassengers();
	
	public boolean savePassenger(Passenger passenger);

	public Passenger getPassengerById(Long id);
	
	public boolean updatePassenger(Passenger passenger);
	
	public boolean deletePassengerById(Long id);

}
