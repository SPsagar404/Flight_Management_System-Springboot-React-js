package com.axyya.service;

import java.util.List;

import com.axyya.model.Flight;
import com.axyya.request.SearchRequest;

public interface IFlightService {
	
	public List<Flight> getAllFlights();
	
	public List<Flight> getAllFlightsInBetweenSourceAndDestination(String from,String to);
	
	public boolean saveFlight(Flight flight);

	
	public Flight getFlightById(Long id);
	
	public boolean updateFlight(Flight flight);
	
	public boolean deleteFlightById(Long id);
	
}
