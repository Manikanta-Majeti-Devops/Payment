package com.onlinereservationapplication.payment;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
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

}
