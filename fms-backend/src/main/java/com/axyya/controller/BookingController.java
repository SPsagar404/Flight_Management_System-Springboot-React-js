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

import com.axyya.constants.BookingConstants;
import com.axyya.constants.FlightConstants;
import com.axyya.model.Booking;
import com.axyya.model.Flight;
import com.axyya.service.IBookingService;

@RestController
@CrossOrigin("*")
public class BookingController {
	
	
	IBookingService service;
	
	private Map<String, String> messages;
	
	
	
	
	public BookingController(IBookingService service, Map<String, String> messages) {
		this.service = service;
		this.messages = messages;
	}

	@PostMapping("/booking")
	public ResponseEntity<String> saveBooking(@RequestBody Booking booking) {
		String responseMsg = FlightConstants.EMPTY_STR;

		boolean isSaved = service.saveBooking(booking);

		if (isSaved) {
			responseMsg = messages.get(BookingConstants.BOOKING_SAVE_SUCC);
		} else {
			responseMsg = messages.get(BookingConstants.BOOKING_SAVE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping("/booking")
	public ResponseEntity<List<Booking>> bookings() {
		List<Booking> allBookings = service.getAllBookings();
		return new ResponseEntity<>(allBookings, HttpStatus.OK);
	}

	@GetMapping("/bookings/{id}")
	public ResponseEntity<Booking> editBooking(@PathVariable Long id) {
		Booking booking = service.getBookingById(id);
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}

	@PutMapping("/booking")
	public ResponseEntity<String> updateBooking(@RequestBody Booking booking) {
		boolean isUpdated = service.updateBooking(booking);

		String msg = FlightConstants.EMPTY_STR;
		if (isUpdated) {
			msg = messages.get(BookingConstants.BOOKING_UPDATE_SUCC);
		} else {
			msg = messages.get(BookingConstants.BOOKING_UPDATE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/booking/{id}")
	public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
		boolean isDeleted = service.deleteBookingById(id);

		String msg = FlightConstants.EMPTY_STR;

		if (isDeleted) {
			msg = messages.get(BookingConstants.BOOKING_DELETE_SUCC);
		} else {
			msg = messages.get(BookingConstants.BOOKING_DELETE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/booking/{userId}")
    public ResponseEntity<?> getAllBookingsOfUser(@PathVariable Long userId)
    {
        return ResponseEntity.ok(service.findAllBookingsOfUser(userId));
    }
	

}
