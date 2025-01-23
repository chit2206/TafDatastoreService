package com.tekarch.TafDatastoreService.Controller;
import com.tekarch.TafDatastoreService.Models.*;
import com.tekarch.TafDatastoreService.Repository.BookingRepository;
import com.tekarch.TafDatastoreService.Service.BookingInterfaceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingInterfaceImpl bookingSvcImpl;
    @Autowired
    BookingRepository bookingRepository;
    public static final Logger log= LogManager.getLogger(BookingController.class);

    public BookingController(BookingInterfaceImpl bookingSvcImpl) {
        this.bookingSvcImpl = bookingSvcImpl;
    }
    @GetMapping
    public ResponseEntity<List<Booking>> getAllTransfers() {
        return new ResponseEntity<>(bookingRepository.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO booking) {
        return new ResponseEntity<>(bookingSvcImpl.createBooking(booking), HttpStatus.OK);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long bookingId) {
        try {
            Booking booking = bookingSvcImpl.retrieveBookingById(bookingId);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Long userId) {
        List<Booking> bookings = bookingSvcImpl.retrieveBookingByUserId(userId);
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        try {
            bookingSvcImpl.cancelBooking(bookingId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PutMapping("/{flightNum}/{seatCnt}")
    public ResponseEntity<Flights> updateAvailableSeats(@PathVariable String flightNum, @PathVariable int seatCnt) {
        return new ResponseEntity<>(bookingSvcImpl.updateSeat(flightNum,seatCnt),HttpStatus.OK);
    }

}