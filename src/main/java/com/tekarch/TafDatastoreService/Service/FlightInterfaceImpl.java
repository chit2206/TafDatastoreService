package com.tekarch.TafDatastoreService.Service;

import com.tekarch.TafDatastoreService.Models.Flights;
import com.tekarch.TafDatastoreService.Repository.FlightsRepository;
import com.tekarch.TafDatastoreService.Service.Interface.FlightInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class FlightInterfaceImpl  implements FlightInterface {
    @Autowired
    FlightsRepository flightRepository;
    @Override
    public Optional<Flights> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Flights updateFlight(Long id, Flights flight) {
        if (!flightRepository.existsById(id)) {
            throw new RuntimeException("Flight not found");
        }
        flight.setId(id);
        return flightRepository.save(flight);
    }
}
