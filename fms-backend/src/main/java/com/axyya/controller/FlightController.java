package com.axyya.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.axyya.constants.FlightConstants;
import com.axyya.model.Flight;
import com.axyya.service.IFlightService;

@RestController
@CrossOrigin("*")
public class FlightController {
	
	
	IFlightService service;
	
	private Map<String, String> messages;
	
	
	
	
	public FlightController(IFlightService service, Map<String, String> messages) {
		this.service = service;
		this.messages = messages;
	}

	@PostMapping("/flight")
	public ResponseEntity<String> saveFlight(@RequestBody Flight flight) {
		String responseMsg = FlightConstants.EMPTY_STR;

		boolean isSaved = service.saveFlight(flight);

		if (isSaved) {
			responseMsg = messages.get(FlightConstants.FLIGHT_SAVE_SUCC);
		} else {
			responseMsg = messages.get(FlightConstants.FLIGHT_SAVE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping("/flight")
	public ResponseEntity<List<Flight>> flights() {
		List<Flight> allFlights = service.getAllFlights();
		return new ResponseEntity<>(allFlights, HttpStatus.OK);
	}

	@GetMapping("/flight/{id}")
	public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
		Flight flight = service.getFlightById(id);
		return new ResponseEntity<>(flight, HttpStatus.OK);
	}

	@PutMapping("/flight")
	public ResponseEntity<String> updateFlight(@RequestBody Flight flight) {
		boolean isUpdated = service.updateFlight(flight);

		String msg = FlightConstants.EMPTY_STR;
		if (isUpdated) {
			msg = messages.get(FlightConstants.FLIGHT_UPDATE_SUCC);
		} else {
			msg = messages.get(FlightConstants.FLIGHT_UPDATE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/flight/{id}")
	public ResponseEntity<String> deleteFlight(@PathVariable Long id) {
		boolean isDeleted = service.deleteFlightById(id);

		String msg = FlightConstants.EMPTY_STR;

		if (isDeleted) {
			msg = messages.get(FlightConstants.FLIGHT_DELETE_SUCC);
		} else {
			msg = messages.get(FlightConstants.FLIGHT_DELETE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/flights/{from}/{to}")
	public ResponseEntity<List<Flight>> findAllFlightBetween(@PathVariable String from,@PathVariable String to) {
		List<Flight> flights = service.getAllFlightsInBetweenSourceAndDestination(from, to);
		return new ResponseEntity<>(flights, HttpStatus.OK);
		
	}

}
