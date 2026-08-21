package com.banking.transactionservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransferRequest {

    @NotBlank(message = "Sender accountNumber cannot be empty")
    private String senderAccountNumber;

    @NotBlank(message = "Receiver accountNumber cannot be empty")
    private String receiverAccountNumber;

    @NotBlank(message = "amount is required")
    @Positive(message = "amount must be positive")
    private BigDecimal amount;

    private String description;
}
