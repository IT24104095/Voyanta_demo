package com.voyanta.repository;

import com.voyanta.entity.Subscription;
import com.voyanta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    
    Optional<Subscription> findByUser(User user);
    
    List<Subscription> findByType(Subscription.SubscriptionType type);
    
    List<Subscription> findByStatus(Subscription.SubscriptionStatus status);
    
    @Query("SELECT s FROM Subscription s WHERE s.user = :user AND s.status = 'ACTIVE'")
    Optional<Subscription> findActiveSubscriptionByUser(@Param("user") User user);
    
    @Query("SELECT s FROM Subscription s WHERE s.endDate < :currentDate AND s.status = 'ACTIVE'")
    List<Subscription> findExpiredActiveSubscriptions(@Param("currentDate") LocalDateTime currentDate);
    
    @Query("SELECT s FROM Subscription s WHERE s.autoRenewal = true AND s.endDate BETWEEN :startDate AND :endDate")
    List<Subscription> findSubscriptionsForAutoRenewal(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT COUNT(s) FROM Subscription s WHERE s.type = :type AND s.status = 'ACTIVE'")
    Long countActiveSubscriptionsByType(@Param("type") Subscription.SubscriptionType type);
}
