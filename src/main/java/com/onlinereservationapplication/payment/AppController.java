package com.onlinereservationapplication.payment;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1")
public class AppController
{
    private final PaymentRepository paymentRepository;

    AppController(PaymentRepository paymentRepository)
    {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("fetch/all/payments")
    public ResponseEntity<List<Payment>> fetchAllPayments()
    {
        return ResponseEntity.ok(paymentRepository.findAll());
    }

    @PostMapping("add/payment/{bookingNumber}")
    public ResponseEntity<String> addPayment(@PathVariable String bookingNumber)
    {
        Payment payment = new Payment();
        payment.setPaymentNumber(String.valueOf(UUID.randomUUID()));
        payment.setBookingNumber(bookingNumber);
        payment.setPaymentDate(LocalDate.now());
        paymentRepository.save(payment);
        return ResponseEntity.ok("Requested Payment Entry added successfully" + payment);
    }

    @PostMapping("edit/payment/{paymentNumber}")
    public ResponseEntity<String> editPayment(@PathVariable String paymentNumber)
    {
        paymentRepository.save(paymentRepository.findByPaymentNumber(paymentNumber));
        return ResponseEntity.ok("Requested Payment Entry updated successfully" + paymentNumber);
    }

    @PostMapping("delete/payment/{paymentNumber}")
    public ResponseEntity<String> deletePayment(@PathVariable String paymentNumber)
    {
        paymentRepository.delete(paymentRepository.findByPaymentNumber(paymentNumber));
        return ResponseEntity.ok("Requested Payment Entry Deleted successfully" + paymentNumber);
    }


}
