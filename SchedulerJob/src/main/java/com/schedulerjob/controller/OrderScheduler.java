package com.schedulerjob.controller;

import com.schedulerjob.services.OrderService;
import com.schedulerjob.services.ReportService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderScheduler {

    private final OrderService orderService;
    private final ReportService reportService;

    public OrderScheduler(OrderService orderService, ReportService reportService) {
        this.orderService = orderService;
        this.reportService = reportService;
    }

    // Run daily at 2 AM
    @Scheduled(cron = "0 0 2 * * ?")
    public void cleanExpiredOrders() {
        System.out.println("🧹 Running expired orders cleanup at " + LocalDateTime.now());
        orderService.deleteExpiredOrders();
    }

    // Run daily at 9 AM
//    @Scheduled(cron = "0 0 9 * * ?")
    @Scheduled(cron = "0 25 23 * * ?", zone = "Asia/Kolkata") // 11:25 PM
    public void sendDailyReport() {
        System.out.println("📨 Running daily report job at " + LocalDateTime.now());
        reportService.generateAndSendReport();
    }

    // For demo: run every 30 seconds
    @Scheduled(fixedRate = 30000)
    public void demoScheduler() {
        System.out.println("⏱ Demo Scheduler running at " + LocalDateTime.now());
    }
}
