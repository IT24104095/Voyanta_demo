package com.voyanta.repository;

import com.voyanta.entity.Feedback;
import com.voyanta.entity.User;
import com.voyanta.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
    List<Feedback> findByUser(User user);
    
    List<Feedback> findByVehicle(Vehicle vehicle);
    
    List<Feedback> findByStatus(Feedback.FeedbackStatus status);
    
    List<Feedback> findByRating(Integer rating);
    
    List<Feedback> findByIsEscalated(Boolean isEscalated);
    
    @Query("SELECT f FROM Feedback f WHERE f.user = :user ORDER BY f.createdAt DESC")
    List<Feedback> findByUserOrderByCreatedAtDesc(@Param("user") User user);
    
    @Query("SELECT f FROM Feedback f WHERE f.vehicle = :vehicle ORDER BY f.createdAt DESC")
    List<Feedback> findByVehicleOrderByCreatedAtDesc(@Param("vehicle") Vehicle vehicle);
    
    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.vehicle = :vehicle AND f.status = 'APPROVED'")
    Double getAverageRatingByVehicle(@Param("vehicle") Vehicle vehicle);
    
    @Query("SELECT COUNT(f) FROM Feedback f WHERE f.vehicle = :vehicle AND f.status = 'APPROVED'")
    Long getFeedbackCountByVehicle(@Param("vehicle") Vehicle vehicle);
    
    @Query("SELECT f FROM Feedback f WHERE f.rating <= :rating AND f.status = 'PENDING'")
    List<Feedback> findLowRatingPendingFeedbacks(@Param("rating") Integer rating);
    
    @Query("SELECT f FROM Feedback f WHERE f.isEscalated = true ORDER BY f.createdAt DESC")
    List<Feedback> findEscalatedFeedbacks();
}
