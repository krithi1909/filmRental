package com.example.filmRental.Service;

import com.example.filmRental.Dto.PaymentDto;
import com.example.filmRental.Entity.Customer;
import com.example.filmRental.Entity.Payment;
import com.example.filmRental.Entity.Staff;
import com.example.filmRental.Repository.CustomerRepository;
import com.example.filmRental.Repository.PaymentRepository;
import com.example.filmRental.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StaffRepository staffRepository;

    public String makeOnePaymentEntry(PaymentDto dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        Staff staff = staffRepository.findById(dto.getStaffId())
            .orElseThrow(() -> new RuntimeException("Staff not found"));

        Payment payment = new Payment();
        payment.setCustomer(customer);
        payment.setStaff(staff);
        payment.setAmount(BigDecimal.valueOf(dto.getAmount()));
        payment.setPaymentDate(dto.getPaymentDate().atStartOfDay());
        payment.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

        paymentRepository.save(payment);
        return "Record Created Successfully";
    }

    public List<Object[]> getRevenueDatewiseAllStores() {
        return paymentRepository.getRevenueDatewiseAllStores();
    }

    public List<Object[]> getRevenueDatewiseByStore(Long storeId) {
        return paymentRepository.getRevenueDatewiseByStore(storeId);
    }

    public List<Object[]> getRevenueFilmsByStore(Long storeId) {
        return paymentRepository.getRevenueFilmsByStore(storeId);
    }

    public List<Object[]> getRevenueOfFilmStoreWise(Long filmId) {
        return paymentRepository.getRevenueOfFilmStoreWise(filmId);
    }

    public List<Object[]> getRevenueAllFilmsAcrossStores() {
        return paymentRepository.getRevenueAllFilmsAcrossStores();
    }
}