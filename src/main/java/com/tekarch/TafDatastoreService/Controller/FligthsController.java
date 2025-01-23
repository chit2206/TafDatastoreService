package com.tekarch.TafDatastoreService.Controller;

import com.tekarch.TafDatastoreService.Models.Flights;
import com.tekarch.TafDatastoreService.Models.Users;
import com.tekarch.TafDatastoreService.Repository.FlightsRepository;
import com.tekarch.TafDatastoreService.Service.FlightInterfaceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/flights")
public class FligthsController {

    @Autowired
    private FlightsRepository flightsRepository;
    @Autowired
    FlightInterfaceImpl flightserviceImpl;

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
        Flights updatedFlight = flightserviceImpl.updateFlight(flightId, flight);
        return ResponseEntity.ok(flightsRepository.save(updatedFlight));
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<List<Flights>> getflightById(Long flightId) {
        return new ResponseEntity<>(flightsRepository.findAllById(flightId), HttpStatus.OK);
    }

}
