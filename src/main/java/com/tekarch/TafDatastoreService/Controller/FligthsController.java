package com.tekarch.TafDatastoreService.Controller;

import com.tekarch.TafDatastoreService.Models.Flights;
import com.tekarch.TafDatastoreService.Models.Users;
import com.tekarch.TafDatastoreService.Repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FligthsController {
    @Autowired
    private FlightsRepository flightsRepository;

    @GetMapping
    public ResponseEntity<List<Flights>> getAllFlights() {
        return ResponseEntity.ok(flightsRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Flights> addFlight(@RequestBody Flights flight) {
        return ResponseEntity.ok(flightsRepository.save(flight));
    }
    @PutMapping("/{flightId}")
    public ResponseEntity<Flights> updateFlight(@PathVariable Long flightId, @RequestBody Flights flight) {
        return flightsRepository.findById(flightId)
                .map(existingFlight -> {
                    // Directly update the fields without applying logic

                            existingFlight.setArrival(flight.getArrival());
                            existingFlight.setDepartureTime(flight.getDepartureTime());
                            existingFlight.setArrivalTime(flight.getArrivalTime());
                            flightsRepository.save(existingFlight);
                    return ResponseEntity.ok(existingFlight);
                }).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{flightid}")
    public ResponseEntity<List<Flights>> getflightById(Long flightid) {
        return new ResponseEntity<>(flightsRepository.findAllById(flightid), HttpStatus.OK);
    }

}
