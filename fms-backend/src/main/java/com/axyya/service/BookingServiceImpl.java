package com.axyya.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axyya.model.Booking;
import com.axyya.model.Flight;
import com.axyya.repository.IBookingRepository;

@Service
public class BookingServiceImpl implements IBookingService{
	
	@Autowired
	IBookingRepository repository;

	@Override
	public List<Booking> getAllBookings() {
	
		return repository.findAll();
	}

	@Override
	public boolean saveBooking(Booking booking) {
		booking.setBooking_date(LocalDate.now());
		
		Booking saved=repository.save(booking);
		
		return saved.getBookingId()!=null;
	}

	@Override
	public Booking getBookingById(Long id) {
		
		Optional<Booking> findById = repository.findById(id);

		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean updateBooking(Booking booking) {
		repository.save(booking); 
		return booking.getBookingId() != null;
	}

	@Override
	public boolean deleteBookingById(Long id) {
		
		boolean status = false;
		try {
			repository.deleteById(id);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Booking> findAllBookingsOfUser(Long userId) {
		
		return repository.findAllByUserId(userId);
	}
	
	
}
