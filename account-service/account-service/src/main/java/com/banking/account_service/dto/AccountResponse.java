package com.banking.account_service.dto;

import com.banking.account_service.entity.AccountStatus;
import com.banking.account_service.entity.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
