package com.voyanta.service;

import com.voyanta.dto.request.BookingRequest;
import com.voyanta.entity.Booking;
import com.voyanta.entity.User;
import com.voyanta.entity.Vehicle;
import com.voyanta.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private VehicleService vehicleService;
    
    @Autowired
    private UserService userService;
    
    public Booking createBooking(BookingRequest bookingRequest, String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Vehicle vehicle = vehicleService.getVehicleById(bookingRequest.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        
        if (vehicle.getStatus() != Vehicle.VehicleStatus.AVAILABLE) {
            throw new RuntimeException("Vehicle is not available for booking");
        }
        
        // Check for conflicts
        if (hasBookingConflict(vehicle, bookingRequest.getStartDate(), bookingRequest.getEndDate())) {
            throw new RuntimeException("Vehicle is already booked for the selected dates");
        }
        
        // Calculate total amount
        BigDecimal totalAmount = calculateBookingAmount(vehicle, bookingRequest.getStartDate(), bookingRequest.getEndDate());
        
        Booking booking = new Booking(user, vehicle, bookingRequest.getStartDate(), 
                bookingRequest.getEndDate(), bookingRequest.getPickupLocation(), 
                bookingRequest.getReturnLocation(), totalAmount);
        
        booking.setSpecialRequests(bookingRequest.getSpecialRequests());
        
        return bookingRepository.save(booking);
    }
    
    public List<Booking> getUserBookings(String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return bookingRepository.findByUserOrderByCreatedAtDesc(user);
    }
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public List<Booking> getBookingsByStatus(Booking.BookingStatus status) {
        return bookingRepository.findByStatus(status);
    }
    
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }
    
    public Booking updateBookingStatus(Long id, Booking.BookingStatus status) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            booking.setStatus(status);
            return bookingRepository.save(booking);
        }
        throw new RuntimeException("Booking not found");
    }
    
    public Booking cancelBooking(Long id, String username) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            
            // Check if user owns the booking or is admin/staff
            if (!booking.getUser().getUsername().equals(username) && 
                !isAdminOrStaff(username)) {
                throw new RuntimeException("You can only cancel your own bookings");
            }
            
            if (booking.getStatus() == Booking.BookingStatus.CANCELLED) {
                throw new RuntimeException("Booking is already cancelled");
            }
            
            if (booking.getStatus() == Booking.BookingStatus.COMPLETED) {
                throw new RuntimeException("Cannot cancel completed booking");
            }
            
            booking.setStatus(Booking.BookingStatus.CANCELLED);
            
            // Make vehicle available again
            Vehicle vehicle = booking.getVehicle();
            vehicle.setStatus(Vehicle.VehicleStatus.AVAILABLE);
            vehicleService.updateVehicle(vehicle);
            
            return bookingRepository.save(booking);
        }
        throw new RuntimeException("Booking not found");
    }
    
    public List<Booking> getBookingsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return bookingRepository.findBookingsByStartDateRange(startDate, endDate);
    }
    
    private boolean hasBookingConflict(Vehicle vehicle, LocalDateTime startDate, LocalDateTime endDate) {
        List<Booking> activeBookings = bookingRepository.findActiveBookingsByVehicle(vehicle);
        
        for (Booking booking : activeBookings) {
            if (booking.getStatus() == Booking.BookingStatus.CONFIRMED || 
                booking.getStatus() == Booking.BookingStatus.ACTIVE) {
                
                if ((startDate.isBefore(booking.getEndDate()) && endDate.isAfter(booking.getStartDate()))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private BigDecimal calculateBookingAmount(Vehicle vehicle, LocalDateTime startDate, LocalDateTime endDate) {
        long hours = ChronoUnit.HOURS.between(startDate, endDate);
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        
        if (hours <= 24) {
            return vehicle.getHourlyRate().multiply(BigDecimal.valueOf(hours));
        } else {
            return vehicle.getDailyRate().multiply(BigDecimal.valueOf(days));
        }
    }
    
    private boolean isAdminOrStaff(String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return user.getRole() == User.Role.ADMIN || user.getRole() == User.Role.STAFF;
    }
}
