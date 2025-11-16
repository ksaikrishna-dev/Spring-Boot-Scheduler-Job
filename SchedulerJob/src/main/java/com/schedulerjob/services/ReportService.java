package com.schedulerjob.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReportService {

    private final EmailService emailService;

    public ReportService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void generateAndSendReport() {
        // Normally: fetch sales data from DB
        String reportContent = "- Orders placed: 12\n- Completed: 10\n- Pending: 2\n- Revenue: $1,250";

        emailService.sendReportEmailWithPdf(
                "ksaikrishna2244@gmail.com",
                "📊 Daily Sales Report",
                reportContent
        );
    }
}
