package com.voyanta.service;

import com.voyanta.dto.response.VehicleResponse;
import com.voyanta.entity.Vehicle;
import com.voyanta.repository.FeedbackRepository;
import com.voyanta.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VehicleService {
    
    @Autowired
    private VehicleRepository vehicleRepository;
    
    @Autowired
    private FeedbackRepository feedbackRepository;
    
    public List<VehicleResponse> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(this::convertToVehicleResponse)
                .collect(Collectors.toList());
    }
    
    public List<VehicleResponse> getAvailableVehicles() {
        return vehicleRepository.findByStatus(Vehicle.VehicleStatus.AVAILABLE).stream()
                .map(this::convertToVehicleResponse)
                .collect(Collectors.toList());
    }
    
    public List<VehicleResponse> getVehiclesByType(Vehicle.VehicleType type) {
        return vehicleRepository.findByType(type).stream()
                .map(this::convertToVehicleResponse)
                .collect(Collectors.toList());
    }
    
    public List<VehicleResponse> getVehiclesByMake(String make) {
        return vehicleRepository.findByMake(make).stream()
                .map(this::convertToVehicleResponse)
                .collect(Collectors.toList());
    }
    
    public List<VehicleResponse> searchVehicles(String searchTerm) {
        return vehicleRepository.findBySearchTerm(searchTerm).stream()
                .map(this::convertToVehicleResponse)
                .collect(Collectors.toList());
    }
    
    public List<VehicleResponse> getVehiclesByPriceRange(BigDecimal minRate, BigDecimal maxRate) {
        return vehicleRepository.findByDailyRateBetween(minRate, maxRate).stream()
                .map(this::convertToVehicleResponse)
                .collect(Collectors.toList());
    }
    
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }
    
    public VehicleResponse getVehicleResponseById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.map(this::convertToVehicleResponse).orElse(null);
    }
    
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
    
    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
    
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
    
    public void updateVehicleStatus(Long id, Vehicle.VehicleStatus status) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);
        if (vehicleOpt.isPresent()) {
            Vehicle vehicle = vehicleOpt.get();
            vehicle.setStatus(status);
            vehicleRepository.save(vehicle);
        }
    }
    
    public List<String> getAllMakes() {
        return vehicleRepository.findDistinctMakes();
    }
    
    public List<String> getModelsByMake(String make) {
        return vehicleRepository.findDistinctModelsByMake(make);
    }
    
    private VehicleResponse convertToVehicleResponse(Vehicle vehicle) {
        VehicleResponse response = new VehicleResponse(vehicle);
        
        // Calculate average rating and total feedbacks
        Double averageRating = feedbackRepository.getAverageRatingByVehicle(vehicle);
        Long totalFeedbacks = feedbackRepository.getFeedbackCountByVehicle(vehicle);
        
        response.setAverageRating(averageRating);
        response.setTotalFeedbacks(totalFeedbacks);
        
        return response;
    }
}
