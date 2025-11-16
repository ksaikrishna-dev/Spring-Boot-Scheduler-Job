package com.schedulerjob.services;

import com.schedulerjob.dao.OrderRepository;
import com.schedulerjob.dtos.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void deleteExpiredOrders() {
        List<Order> expiredOrders = orderRepository.findByStatusAndExpiryAtBefore("PENDING", LocalDateTime.now());
        if (!expiredOrders.isEmpty()) {
            orderRepository.deleteAll(expiredOrders);
            System.out.println("Deleted " + expiredOrders.size() + " expired orders");
        } else {
            System.out.println("No expired orders found");
        }
    }

    public void createSampleOrder(String status, int minutesValid) {
        LocalDateTime now = LocalDateTime.now();
        Order order = new Order(status, now, now.plusMinutes(minutesValid));
        orderRepository.save(order);
    }
}
