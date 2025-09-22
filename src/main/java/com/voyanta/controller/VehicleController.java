package com.voyanta.controller;

import com.voyanta.dto.response.VehicleResponse;
import com.voyanta.entity.Vehicle;
import com.voyanta.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public")
public class VehicleController {
    
    @Autowired
    private VehicleService vehicleService;
    
    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
        List<VehicleResponse> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }
    
    @GetMapping("/vehicles/available")
    public ResponseEntity<List<VehicleResponse>> getAvailableVehicles() {
        List<VehicleResponse> vehicles = vehicleService.getAvailableVehicles();
        return ResponseEntity.ok(vehicles);
    }
    
    @GetMapping("/vehicles/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable Long id) {
        VehicleResponse vehicle = vehicleService.getVehicleResponseById(id);
        if (vehicle != null) {
            return ResponseEntity.ok(vehicle);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Vehicle not found");
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/vehicles/type/{type}")
    public ResponseEntity<List<VehicleResponse>> getVehiclesByType(@PathVariable Vehicle.VehicleType type) {
        List<VehicleResponse> vehicles = vehicleService.getVehiclesByType(type);
        return ResponseEntity.ok(vehicles);
    }
    
    @GetMapping("/vehicles/make/{make}")
    public ResponseEntity<List<VehicleResponse>> getVehiclesByMake(@PathVariable String make) {
        List<VehicleResponse> vehicles = vehicleService.getVehiclesByMake(make);
        return ResponseEntity.ok(vehicles);
    }
    
    @GetMapping("/vehicles/search")
    public ResponseEntity<List<VehicleResponse>> searchVehicles(@RequestParam String q) {
        List<VehicleResponse> vehicles = vehicleService.searchVehicles(q);
        return ResponseEntity.ok(vehicles);
    }
    
    @GetMapping("/vehicles/price-range")
    public ResponseEntity<List<VehicleResponse>> getVehiclesByPriceRange(
            @RequestParam BigDecimal minRate, 
            @RequestParam BigDecimal maxRate) {
        List<VehicleResponse> vehicles = vehicleService.getVehiclesByPriceRange(minRate, maxRate);
        return ResponseEntity.ok(vehicles);
    }
    
    @GetMapping("/vehicles/makes")
    public ResponseEntity<List<String>> getAllMakes() {
        List<String> makes = vehicleService.getAllMakes();
        return ResponseEntity.ok(makes);
    }
    
    @GetMapping("/vehicles/models")
    public ResponseEntity<List<String>> getModelsByMake(@RequestParam String make) {
        List<String> models = vehicleService.getModelsByMake(make);
        return ResponseEntity.ok(models);
    }
}
