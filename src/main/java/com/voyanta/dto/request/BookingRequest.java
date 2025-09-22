package com.voyanta.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

public class BookingRequest {
    
    @NotNull
    private Long vehicleId;
    
    @NotNull
    @Future
    private LocalDateTime startDate;
    
    @NotNull
    @Future
    private LocalDateTime endDate;
    
    @NotNull
    private String pickupLocation;
    
    @NotNull
    private String returnLocation;
    
    private String specialRequests;
    
    public BookingRequest() {}
    
    public BookingRequest(Long vehicleId, LocalDateTime startDate, LocalDateTime endDate,
                         String pickupLocation, String returnLocation) {
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pickupLocation = pickupLocation;
        this.returnLocation = returnLocation;
    }
    
    public Long getVehicleId() {
        return vehicleId;
    }
    
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
    
    public LocalDateTime getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    
    public LocalDateTime getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    
    public String getPickupLocation() {
        return pickupLocation;
    }
    
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
    
    public String getReturnLocation() {
        return returnLocation;
    }
    
    public void setReturnLocation(String returnLocation) {
        this.returnLocation = returnLocation;
    }
    
    public String getSpecialRequests() {
        return specialRequests;
    }
    
    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }
}
