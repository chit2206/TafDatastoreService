package com.tekarch.TafDatastoreService.Service;
import java.util.Optional;

import com.tekarch.TafDatastoreService.Models.*;
import com.tekarch.TafDatastoreService.Repository.BookingRepository;
import com.tekarch.TafDatastoreService.Repository.FlightsRepository;
import com.tekarch.TafDatastoreService.Repository.UsersRepository;
import com.tekarch.TafDatastoreService.Service.Interface.BookingInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class BookingInterfaceImpl implements BookingInterface {
    public static final Logger log = LogManager.getLogger(BookingInterfaceImpl.class);
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private FlightsRepository flightRepository;

    @Override
    public Booking createBooking(BookingDTO booking) {

        Optional<Flights> flightDetail = flightRepository.findById(booking.getFlightId());
        Optional<Users> user = userRepository.findById(booking.getUserId());

        Booking booking1 = new Booking();
        booking1.setUser(user.get());
        booking1.setFlight(flightDetail.get());
        booking1.setStatus("BOOKED");
        return bookingRepository.save(booking1);
        //Check if available seat is >0, if yes, go for booking else return error
//        try {
//            if (availableSeats > 0) {
//                //Go for booking
//                //Based on booking type (Booked or Cancellation), adjust the available seat count
//                int adjustedSeatCount = availableSeats;
//                if (booking.getStatus().equals(BookingStatus.Booked)) {
//                    //Decrease the available seat by 1
//                    adjustedSeatCount = availableSeats - 1;
//
//                }
//                flightRepository.updateAvailableSeats(flightNum, adjustedSeatCount);
//
//                //Create final booking object for saving in DB
//                booking.setUser(user.get());
//                booking.setFlight(flightDetail);
//
//                Booking finalBookingObj = bookingRepository.save(booking);
//                bookRes.setBooking(finalBookingObj);
//                bookRes.setHttpStatus(HttpStatus.CREATED);
//                return bookRes;
//            } else {
//                log.info("No available seats. Try another flight");
//                bookRes.setBooking(booking);
//                bookRes.setHttpStatus(HttpStatus.BAD_REQUEST);
//                return bookRes;
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            log.error("Error booking a flight");
//            bookRes.setBooking(booking);
//            bookRes.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
//            return bookRes;
//        }
    }

    @Override
    public Booking retrieveBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id:" + bookingId));
    }

    @Override
    public List retrieveBookingByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public void cancelBooking(Long bookingId) {
//        Booking booking = bookingRepository.findById(bookingId)
//                .orElseThrow(() -> new RuntimeException("Booking not found with bookingId:" + bookingId));
//
//        if (booking.getStatus().equals(BookingStatus.Cancelled)) {
//            throw new RuntimeException("Booking is already cancelled");
//        }
//
//        Flights flight = booking.getFlight();
//
//        //Get the available seat count for the flight
//        int seatCount = flightRepository.getAvailableSeats(flight.getFlightNumber());
//
//        //Update the revised seat count
//        flightRepository.updateAvailableSeats(flight.getFlightNumber(), seatCount+1);
//
//        booking.setStatus("cancelled");
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public Flights updateSeat(String flightNum, int seatCnt) {
        flightRepository.updateAvailableSeats(flightNum, seatCnt);
        Flights flightDetail = flightRepository.findByFlightNumber(flightNum);
        return flightDetail;
    }
}
