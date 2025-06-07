package com.banking.app.LedgerX.controller;

import com.banking.app.LedgerX.entity.Account;
import com.banking.app.LedgerX.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.ok(createdAccount);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Optional<Account> account = accountService.getAccountById(id);
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-account-number/{accountNumber}")
    public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable int accountNumber) {
        Optional<Account> account = accountService.getAccountByAccountNumber(accountNumber);
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/deposit/{accountNumber}")
    public ResponseEntity<Account> deposit(@PathVariable int accountNumber, @RequestParam double amount) {
        Account account = accountService.deposit(accountNumber, amount);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/withdraw/{accountNumber}")
    public ResponseEntity<Account> withdraw(@PathVariable int accountNumber, @RequestParam double amount) {
        Account account = accountService.withdraw(accountNumber, amount);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<Double> getBalance(@PathVariable int accountNumber) {
        Double balance = accountService.getBalance(accountNumber);
        return ResponseEntity.ok(balance);
    }
}