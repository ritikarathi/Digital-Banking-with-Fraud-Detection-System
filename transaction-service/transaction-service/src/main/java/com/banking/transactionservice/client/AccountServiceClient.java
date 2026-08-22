package com.banking.transactionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "account-service",url = "${account.service.url}")
public class AccountServiceClient {

    @PutMapping("/api/v1/accounts/{accountNumber}/deduct")
    public String deductBalance(@PathVariable String accountNumber, @RequestParam BigDecimal amount);
}
