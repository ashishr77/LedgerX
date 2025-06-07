package com.banking.app.LedgerX.repository;

import com.banking.app.LedgerX.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findByAccountNumber(int accountNumber);

}
