package com.banking.account_service.controller;

import com.banking.account_service.dto.AccountResponse;
import com.banking.account_service.dto.CreateAccountRequest;
import com.banking.account_service.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.createAccount(request));
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable String accountNumber){
        return ResponseEntity.ok(accountService.getAccount(accountNumber));
    }

    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<AccountResponse> getBalance(@PathVariable String accountNumber){
        return ResponseEntity.ok(accountService.getBalance(accountNumber));
    }

    @PutMapping("/{accountNumber}/block")
    public ResponseEntity<AccountResponse> blockAccount(@PathVariable String accountNumber){
        accountService.blockAccount(accountNumber);
        return ResponseEntity.ok("Account Blocked Successfully");
    }

    //SAGA STEP 1 (deduct balance) called by transaction service when transfer is initiated

    @PutMapping("/{accountNumber}/deduct")
    public ResponseEntity<AccountResponse> deductBalance(@PathVariable String accountNumber , @RequestParam BigDecimal amount){
        accountService.deductBalance(accountNumber,amount);
        return ResponseEntity.ok("Amount deducted Successfully");
    }

    // Saga step-4 Compensating transaction endpoint called by transaction service
    /* if fraud detected then we will refund the receiver
    otherwise send the amount to receiver (credit)
     */

    @PutMapping("/{accountNumber}/credit")
    public ResponseEntity<AccountResponse> creditBalance(@PathVariable String accountNumber , @RequestParam BigDecimal amount){
        accountService.creditBalance(accountNumber,amount);
        return ResponseEntity.ok("Amount credited Successfully");
    }









}
