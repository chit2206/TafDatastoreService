package com.tekarch.TafDatastoreService.Models;

import org.springframework.http.HttpStatus;

public class BookingResponse {
    HttpStatus httpStatus;
    Booking booking;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
