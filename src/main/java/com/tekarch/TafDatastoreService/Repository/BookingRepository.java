package com.tekarch.TafDatastoreService.Repository;

import com.tekarch.TafDatastoreService.Models.Booking;
import com.tekarch.TafDatastoreService.Models.Flights;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findAllById(Long id);
    Booking deleteById(long id);
    List<Booking> findByFlightId(Long id);



}
