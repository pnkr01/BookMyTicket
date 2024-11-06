package com.fil.TicketBooking.controller;

import com.fil.TicketBooking.model.Location;
import com.fil.TicketBooking.service.LocationService;
import com.fil.TicketBooking.serviceimpl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    @Autowired
    private LocationServiceImpl locationService;

    @GetMapping("/get-all-locations")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/get-location-by-id/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Location location = locationService.getLocationById(id);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

//    @GetMapping("/get-location-by-name/{city}")
//    public ResponseEntity<Location> getLocationByName(@PathVariable String city) {
//        return ResponseEntity.ok((Location) locationService.getLocationsByCity(city));
//    }

    @PostMapping("/create-location")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location createdLocation = locationService.createLocation(location);
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }
    @GetMapping("/get-location-by-name/{city}")
    public ResponseEntity<List<Location>> getLocationsByName(@PathVariable String city) {
        List<Location> locations = locationService.getLocationsByCity(city);
        if (locations.isEmpty()) {
            return ResponseEntity.notFound().build();  // Return 404 if no locations found
        }
        return ResponseEntity.ok(locations);  // Return the list of locations
    }


    @PutMapping("/update-location/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        Location updatedLocation = locationService.updateLocation(id, location);
        return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
    }

    @DeleteMapping("/delete-location/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
