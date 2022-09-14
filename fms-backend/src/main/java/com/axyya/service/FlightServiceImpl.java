package com.axyya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axyya.model.Flight;
import com.axyya.repository.IFlightRepository;

@Service
public class FlightServiceImpl implements IFlightService {
	
	@Autowired
	IFlightRepository repository;

	@Override
	public List<Flight> getAllFlights() {
			
		return repository.findAll();
	}

	@Override
	public List<Flight> getAllFlightsInBetweenSourceAndDestination(String from, String to) {
		List<Flight> findFlights = repository.findFlights(from, to);
		return findFlights;
	}

	@Override
	public boolean saveFlight(Flight flight) {
		
		Flight saved=repository.save(flight);
		
		return saved.getFlightId()!=null;
	}

	@Override
	public Flight getFlightById(Long id) {
		Optional<Flight> findById = repository.findById(id);

		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean updateFlight(Flight flight) {
		repository.save(flight); // (upsert)
		return flight.getFlightId() != null;
	}

	@Override
	public boolean deleteFlightById(Long id) {
		
		boolean status = false;
		try {
			repository.deleteById(id);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
}