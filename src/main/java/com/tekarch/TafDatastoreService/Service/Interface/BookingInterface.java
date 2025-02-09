package com.tekarch.TafDatastoreService.Service.Interface;

import com.tekarch.TafDatastoreService.Models.Booking;
import com.tekarch.TafDatastoreService.Models.BookingDTO;
import com.tekarch.TafDatastoreService.Models.BookingResponse;
import com.tekarch.TafDatastoreService.Models.Flights;

import java.util.List;

public interface BookingInterface {
    Booking createBooking(BookingDTO booking);
    //List<Bookings> retrieveAllBookings();
    Booking retrieveBookingById(Long bookingId) throws Exception;
    List retrieveBookingByUserId(Long userId);
    void cancelBooking(Long bookingId);
    Flights updateSeat(String flightNum, int seatCnt);


}
