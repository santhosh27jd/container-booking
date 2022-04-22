package com.container.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.container.model.Booking;
import com.container.repository.BookingRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class BookingService
 * @author santhoshkumardurairaj
 *
 */
@Service
public class BookingService {

	/**
	 * BookingRepository object is injected
	 */
	@Autowired
	private BookingRepository bookingRepository;

	public Flux<Booking> findAll() {
		// TODO Auto-generated method stub
		return bookingRepository.findAll();
	}

	public Mono<Booking> save(Booking booking) {
		// TODO Auto-generated method stub
		return bookingRepository.save(booking);
	}
}
