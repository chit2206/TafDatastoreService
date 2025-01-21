package com.tekarch.TafDatastoreService.Repository;

import com.tekarch.TafDatastoreService.Models.Flights;
import com.tekarch.TafDatastoreService.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FlightsRepository extends JpaRepository<Flights, Long> {
    List<Flights> findAllById(Long id);
    Flights findByFlightNumber(String flightNumber);

    @Transactional
    void deleteByFlightNumber(String flightNumber);

    @Transactional
    @Query(value = "SELECT available_seats FROM flights WHERE flight_number = :flightNum", nativeQuery = true)
        //Fetch the number of available seats based on flight number
    int getAvailableSeats(String flightNum);

    @Transactional
    @Modifying
    @Query(value = "UPDATE flights SET available_seats = :adjustedSeatCnt WHERE flight_number = :flightNum", nativeQuery = true)
        //Fetch the number of available seats based on flight number
    void updateAvailableSeats(String flightNum, int adjustedSeatCnt);



}
