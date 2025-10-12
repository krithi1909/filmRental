package com.example.filmRental.Dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class PaymentDto {

    @NotNull
    private Long customerId;

    @NotNull
    private Byte staffId;

    @NotNull
    private Double amount;

    @NotNull
    private LocalDate paymentDate;

    // Getters and Setters
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Byte getStaffId() { return staffId; }
    public void setStaffId(Byte staffId) { this.staffId = staffId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    
}