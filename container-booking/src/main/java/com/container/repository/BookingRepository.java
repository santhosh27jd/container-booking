package com.container.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.container.model.Booking;

@Repository
public interface BookingRepository extends ReactiveCassandraRepository<Booking, Integer>{
	
}
