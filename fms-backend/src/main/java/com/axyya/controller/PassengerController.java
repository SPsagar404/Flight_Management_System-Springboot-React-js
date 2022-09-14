package com.axyya.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.axyya.constants.FlightConstants;
import com.axyya.constants.PassengerConstants;
import com.axyya.model.Flight;
import com.axyya.model.Passenger;
import com.axyya.service.IPassengerService;

public class PassengerController {
	
	IPassengerService service;
	
	private Map<String, String> messages;

	public PassengerController(IPassengerService service, Map<String, String> messages) {
		super();
		this.service = service;
		this.messages = messages;
	}
	
	@PostMapping("/passenger")
	public ResponseEntity<String> saveFlight(@RequestBody Passenger passenger) {
		String responseMsg = PassengerConstants.EMPTY_STR;

		boolean isSaved = service.savePassenger(passenger);

		if (isSaved) {
			responseMsg = messages.get(PassengerConstants.PASSENGER_SAVE_SUCC);
		} else {
			responseMsg = messages.get(PassengerConstants.PASSENGER_SAVE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}
	
	@GetMapping("/passenger")
	public ResponseEntity<List<Passenger>> flights() {
		List<Passenger> allPassengers = service.getAllPassengers();
		return new ResponseEntity<>(allPassengers, HttpStatus.OK);
	}
	
	@GetMapping("/passenger/{id}")
	public ResponseEntity<Passenger> getFlightById(@PathVariable Long id) {
		Passenger passenger = service.getPassengerById(id);
		return new ResponseEntity<>(passenger, HttpStatus.OK);
	}
	
	@PutMapping("/passenger")
	public ResponseEntity<String> updateFlight(@RequestBody Passenger passenger) {
		boolean isUpdated = service.updatePassenger(passenger);

		String msg = PassengerConstants.EMPTY_STR;
		if (isUpdated) {
			msg = messages.get(PassengerConstants.PASSENGER_UPDATE_SUCC);
		} else {
			msg = messages.get(PassengerConstants.PASSENGER_UPDATE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/passenger/{id}")
	public ResponseEntity<String> deleteFlight(@PathVariable Long id) {
		boolean isDeleted = service.deletePassengerById(id);

		String msg = PassengerConstants.EMPTY_STR;

		if (isDeleted) {
			msg = messages.get(PassengerConstants.PASSENGER_DELETE_SUCC);
		} else {
			msg = messages.get(PassengerConstants.PASSENGER_DELETE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
}
