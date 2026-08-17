package com.banking.account_service.dto;

import com.banking.account_service.entity.AccountStatus;
import com.banking.account_service.entity.AccountType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountResponse {

    private String id;


    private String accountNumber;

    private String accountHolderName;

    private String email;

    private String phone;

    @Column(nullable = false)
    private AccountType accountType;

    @Column(nullable = false)
    private AccountStatus accountStatus;

    private BigDecimal balance;

    private BigDecimal DailyTransactionLimit;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
