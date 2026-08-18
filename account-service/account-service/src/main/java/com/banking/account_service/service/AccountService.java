package com.banking.account_service.service;

import com.banking.account_service.Repository.AccountRepository;
import com.banking.account_service.dto.AccountResponse;
import com.banking.account_service.dto.CreateAccountRequest;
import com.banking.account_service.entity.Account;
import com.banking.account_service.entity.AccountStatus;
import com.banking.account_service.entity.AccountType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;

@Service
@AllArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private static SecureRandom secureRandom = new SecureRandom();

    public AccountResponse createAccount(@Valid CreateAccountRequest request) {
        log.info("creating account for :{}",request.getEmail());

        if(accountRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Account already exists for email:{}"+request.getEmail());
        }
        Account account=new Account();
        account.setAccountHolderName(request.getAccountHolderName());
        account.setEmail(request.getEmail());
        account.setPhone(request.getPhone());
        account.setAccountType(request.getAccountType());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setBalance(request.getInitialDeposit());
        account.setAccountNumber(generateAccountNumber());
        account.setDailyTransactionLimit(
                request.getAccountType() == AccountType.SAVINGS
                ? new BigDecimal("100000")
                        : new BigDecimal("500000")
        );
        Account savedAccount = accountRepository.save(account);
        log.info("Account Created :{}",savedAccount.getAccountNumber());

        return mapToResponse(savedAccount);

    }

    private String generateAccountNumber(){
        String accountNumber ;

        do{
            long number =secureRandom.nextLong(1000000000000L);
            accountNumber= String.format("%012d",number);
        }
        while(accountRepository.existsByAccountNumber(accountNumber));
            return accountNumber;

    }
    private AccountResponse mapToResponse(Account account) {

        AccountResponse response = new AccountResponse();

        response.setId(account.getId());
        response.setAccountNumber(account.getAccountNumber());
        response.setAccountHolderName(account.getAccountHolderName());
        response.setEmail(account.getEmail());
        response.setPhone(account.getPhone());
        response.setAccountType(account.getAccountType());
        response.setAccountStatus(account.getAccountStatus());
        response.setBalance(account.getBalance());
        response.setDailyTransactionLimit(account.getDailyTransactionLimit());
        response.setCreatedAt(account.getCreatedAt());

        return response;
    }

    public AccountResponse getAccount(String accountNumber) {
        Account account =accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(()-> new RuntimeException("Account Not Found"));

        return mapToResponse(account);
    }

    public BigDecimal getBalance(String accountNumber) {
        Account account =accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(()-> new RuntimeException("Account Not Found"));

        return account.getBalance();
    }

}
