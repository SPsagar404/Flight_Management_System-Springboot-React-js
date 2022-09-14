package com.axyya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.axyya.model.Passenger;
import com.axyya.repository.IPassengerRepository;

public class PassengerServiceImpl implements IPassengerService {
	
	@Autowired
	IPassengerRepository repository;

	@Override
	public List<Passenger> getAllPassengers() {
		
		return repository.findAll();
	}

	@Override
	public boolean savePassenger(Passenger passenger) {
		
		Passenger saved=repository.save(passenger);
		
		return saved.getPassengerId()!=null;
	}

	@Override
	public Passenger getPassengerById(Long id) {
		Optional<Passenger> findById = repository.findById(id);

		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean updatePassenger(Passenger passenger) {
		repository.save(passenger); // (upsert)
		return passenger.getPassengerId() != null;
	}

	@Override
	public boolean deletePassengerById(Long id) {
		
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
