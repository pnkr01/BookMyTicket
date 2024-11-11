package com.fil.TicketBooking.serviceimpl;
import com.fil.TicketBooking.dto.LocationDTO;
import com.fil.TicketBooking.model.Location;
import com.fil.TicketBooking.repository.LocationRepository;
import com.fil.TicketBooking.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Long id, Location location) {
        Optional<Location> existingLocation = locationRepository.findById(id);
        if (existingLocation.isPresent()) {
            location.setLocationId(id);
            return locationRepository.save(location);
        }
        return null; // or throw an exception
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public Location getLocationById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        List<Location> allLocations = locationRepository.findAll();
        return allLocations.stream()
                .map(location -> new LocationDTO(location.getLocationId(),location.getLocationName(), location.getDescription(), location.getLocationCity())) // Assuming a constructor in LocationDTO that accepts Location
                .collect(Collectors.toList());
    }

    @Override
    public List<Location> getLocationsByCity(String city) {
        return locationRepository.findByLocationName(city);
    }
}

