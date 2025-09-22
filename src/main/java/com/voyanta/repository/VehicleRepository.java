package com.voyanta.repository;

import com.voyanta.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    
    List<Vehicle> findByStatus(Vehicle.VehicleStatus status);
    
    List<Vehicle> findByType(Vehicle.VehicleType type);
    
    List<Vehicle> findByMake(String make);
    
    List<Vehicle> findByMakeAndModel(String make, String model);
    
    @Query("SELECT v FROM Vehicle v WHERE v.dailyRate BETWEEN :minRate AND :maxRate")
    List<Vehicle> findByDailyRateBetween(@Param("minRate") BigDecimal minRate, @Param("maxRate") BigDecimal maxRate);
    
    @Query("SELECT v FROM Vehicle v WHERE v.status = :status AND v.type = :type")
    List<Vehicle> findByStatusAndType(@Param("status") Vehicle.VehicleStatus status, @Param("type") Vehicle.VehicleType type);
    
    @Query("SELECT v FROM Vehicle v WHERE v.make LIKE %:searchTerm% OR v.model LIKE %:searchTerm% OR v.color LIKE %:searchTerm%")
    List<Vehicle> findBySearchTerm(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT DISTINCT v.make FROM Vehicle v ORDER BY v.make")
    List<String> findDistinctMakes();
    
    @Query("SELECT DISTINCT v.model FROM Vehicle v WHERE v.make = :make ORDER BY v.model")
    List<String> findDistinctModelsByMake(@Param("make") String make);
}
