package com.schedulerjob.dao;

import com.schedulerjob.dtos.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatusAndExpiryAtBefore(String status, LocalDateTime now);
}
