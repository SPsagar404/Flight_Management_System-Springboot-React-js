package com.axyya.service;

import java.util.List;

import com.axyya.model.Booking;

public interface IBookingService {
	
	public List<Booking> getAllBookings();
	
	public boolean saveBooking(Booking booking);

	public Booking getBookingById(Long id);
	
	public boolean updateBooking(Booking booking);
	
	public boolean deleteBookingById(Long id);
	
	 List<Booking> findAllBookingsOfUser(Long userId);


}
