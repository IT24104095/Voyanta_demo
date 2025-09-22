package com.voyanta.repository;

import com.voyanta.entity.Booking;
import com.voyanta.entity.User;
import com.voyanta.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    List<Booking> findByUser(User user);
    
    List<Booking> findByVehicle(Vehicle vehicle);
    
    List<Booking> findByStatus(Booking.BookingStatus status);
    
    @Query("SELECT b FROM Booking b WHERE b.user = :user ORDER BY b.createdAt DESC")
    List<Booking> findByUserOrderByCreatedAtDesc(@Param("user") User user);
    
    @Query("SELECT b FROM Booking b WHERE b.vehicle = :vehicle AND b.status IN ('CONFIRMED', 'ACTIVE')")
    List<Booking> findActiveBookingsByVehicle(@Param("vehicle") Vehicle vehicle);
    
    @Query("SELECT b FROM Booking b WHERE b.startDate <= :date AND b.endDate >= :date AND b.status IN ('CONFIRMED', 'ACTIVE')")
    List<Booking> findBookingsByDateRange(@Param("date") LocalDateTime date);
    
    @Query("SELECT b FROM Booking b WHERE b.startDate BETWEEN :startDate AND :endDate")
    List<Booking> findBookingsByStartDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT b FROM Booking b WHERE b.pickupLocation = :location OR b.returnLocation = :location")
    List<Booking> findByLocation(@Param("location") String location);
    
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.vehicle = :vehicle AND b.status = :status")
    Long countByVehicleAndStatus(@Param("vehicle") Vehicle vehicle, @Param("status") Booking.BookingStatus status);
    
    @Query("SELECT b FROM Booking b WHERE b.user = :user AND b.status = :status")
    List<Booking> findByUserAndStatus(@Param("user") User user, @Param("status") Booking.BookingStatus status);
}
