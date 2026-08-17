package com.banking.account_service.dto;

import com.banking.account_service.entity.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequest {

    @NotBlank(message = "Account Holder Name is required")
    private String accountHolderName;

    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "phone is required")
    private String phone;

    @NotNull(message = "AccountType is Required")
    private AccountType accountType;

    @NotNull(message = "initial deposit is required")
    @Positive(message = "initial deposit must be positive")
    private BigDecimal initialDeposit;

}
