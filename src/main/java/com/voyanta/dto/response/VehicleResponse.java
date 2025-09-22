package com.voyanta.dto.response;

import com.voyanta.entity.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VehicleResponse {
    
    private Long id;
    private String make;
    private String model;
    private Vehicle.VehicleType type;
    private String licensePlate;
    private String year;
    private String color;
    private BigDecimal dailyRate;
    private BigDecimal hourlyRate;
    private Vehicle.VehicleStatus status;
    private String description;
    private String imageUrl;
    private LocalDateTime createdAt;
    private Double averageRating;
    private Long totalFeedbacks;
    
    public VehicleResponse() {}
    
    public VehicleResponse(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.make = vehicle.getMake();
        this.model = vehicle.getModel();
        this.type = vehicle.getType();
        this.licensePlate = vehicle.getLicensePlate();
        this.year = vehicle.getYear();
        this.color = vehicle.getColor();
        this.dailyRate = vehicle.getDailyRate();
        this.hourlyRate = vehicle.getHourlyRate();
        this.status = vehicle.getStatus();
        this.description = vehicle.getDescription();
        this.imageUrl = vehicle.getImageUrl();
        this.createdAt = vehicle.getCreatedAt();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getMake() {
        return make;
    }
    
    public void setMake(String make) {
        this.make = make;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public Vehicle.VehicleType getType() {
        return type;
    }
    
    public void setType(Vehicle.VehicleType type) {
        this.type = type;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }
    
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    
    public String getYear() {
        return year;
    }
    
    public void setYear(String year) {
        this.year = year;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public BigDecimal getDailyRate() {
        return dailyRate;
    }
    
    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }
    
    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }
    
    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
    public Vehicle.VehicleStatus getStatus() {
        return status;
    }
    
    public void setStatus(Vehicle.VehicleStatus status) {
        this.status = status;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public Double getAverageRating() {
        return averageRating;
    }
    
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
    
    public Long getTotalFeedbacks() {
        return totalFeedbacks;
    }
    
    public void setTotalFeedbacks(Long totalFeedbacks) {
        this.totalFeedbacks = totalFeedbacks;
    }
}
