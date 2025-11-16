package com.schedulerjob.services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.itextpdf.layout.Document;
import java.io.ByteArrayOutputStream;

@Service
public class EmailService {

    @Autowired
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendReportEmailWithPdf(String to, String subject, String body) {
        try {
            // Step 1: Generate PDF in memory
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("📊 Daily Sales Report"));
            document.add(new Paragraph("Generated at: " + java.time.LocalDateTime.now()));
            document.add(new Paragraph(""));
            document.add(new Paragraph(body));

            document.close();

            // Step 2: Prepare Email with attachment
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom("ksaikrishna2244@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText("Please find attached the Daily Sales Report (PDF).");

            helper.addAttachment("DailySalesReport.pdf",
                    new org.springframework.core.io.ByteArrayResource(baos.toByteArray()));

            // Step 3: Send email
            mailSender.send(mimeMessage);

            System.out.println("📧 Email with PDF attachment sent to " + to);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Failed to send email with PDF: " + e.getMessage());
        }
    }
}
