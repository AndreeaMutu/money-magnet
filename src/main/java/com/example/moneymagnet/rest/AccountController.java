package com.example.moneymagnet.rest;

import com.example.moneymagnet.service.Account;
import com.example.moneymagnet.service.AccountService;
import com.example.moneymagnet.model.CreateAccountRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account create(CreateAccountRequest request) {
        if (request.balance() == null) {
            return accountService.createAccount(request.name(), request.currency());
        }
        return accountService.createAccount(request.name(), request.currency(), request.balance());
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable("id") String accountNo) {
        return accountService.findByAccountNo(accountNo);
    }

    @GetMapping
    public List<Account> findAll() {
        return accountService.getAll();
    }

    @PostMapping("/{id}/add-money")
    public Account add(@PathVariable("id") String accountNo, double amount) {

        return accountService.addMoney(accountNo, amount);
    }

    @PostMapping("/{id}/withdraw-money")
    public Account withdraw(@PathVariable("id") String accountNo, double amount) {
        return accountService.withdrawMoney(accountNo, amount);
    }
}
