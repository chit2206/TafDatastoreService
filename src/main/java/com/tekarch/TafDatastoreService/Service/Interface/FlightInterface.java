package com.tekarch.TafDatastoreService.Service.Interface;

import com.tekarch.TafDatastoreService.Models.Flights;

import java.util.Optional;

public interface FlightInterface {
    Optional<Flights> findById(Long id);
}
