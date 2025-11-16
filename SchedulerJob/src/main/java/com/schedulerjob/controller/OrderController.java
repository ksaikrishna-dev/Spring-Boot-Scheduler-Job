package com.schedulerjob.controller;

import com.schedulerjob.dao.OrderRepository;
import com.schedulerjob.dtos.Order;
import com.schedulerjob.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public OrderController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    // Fetch all orders --> http://localhost:8080/api/orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Create a new order (valid for given minutes) --> http://localhost:8080/api/orders?status=PENDING&validMinutes=1
    @PostMapping
    public Order createOrder(@RequestParam String status, @RequestParam int validMinutes) {
        orderService.createSampleOrder(status, validMinutes);
        return orderRepository.findAll().get(orderRepository.findAll().size() - 1);
    }

    // Delete expired orders manually   --> http://localhost:8080/api/orders/expired
    @DeleteMapping("/expired")
    public String deleteExpiredOrders() {
        orderService.deleteExpiredOrders();
        return "Expired orders cleanup executed";
    }
}
