//package com.schedulerjob.services;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final OrderService orderService;
//
//    public DataLoader(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @Override
//    public void run(String... args) {
//        // Insert sample orders
//        orderService.createSampleOrder("PENDING", -10); // already expired
//        orderService.createSampleOrder("PENDING", 30); // still valid
//        orderService.createSampleOrder("COMPLETED", 60); // completed order
//        System.out.println("✅ Sample Orders inserted into DB");
//    }
//}
