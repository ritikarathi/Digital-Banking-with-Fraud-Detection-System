package com.banking.account_service.Repository;

import com.banking.account_service.entity.Account;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    boolean existsByEmail(@NotBlank(message = "Email is Required") String email);

    boolean existsByAccountNumber(String accountNumber);

   Optional<Account> findByAccountNumber(String accountNumber);
}
