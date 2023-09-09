package com.example.moneymagnet.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {
    private final Map<String, Account> accounts = new HashMap<>();

    public Account createAccount(String clientName, String currency) {
        return createAccount(clientName, currency, 0.0);
    }

    public Account createAccount(String clientName, String currency, Double initialBalance) {
        if (clientName == null) {
            throw new IllegalArgumentException("Client name must be provided!");
        }
        Account account = new Account(clientName, Currency.getInstance(currency), initialBalance);
        accounts.put(account.getAccountNo(), account);
        return account;
    }

    public Account findByAccountNo(String accountNo) {
        if (accountNo == null) {
            throw new IllegalArgumentException("Account number must not be null");
        }
        return accounts.get(accountNo);
    }

    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>(this.accounts.values());
        accounts.sort(Comparator.comparingDouble(Account::getBalance).reversed());
        return accounts;
    }

    public Account addMoney(String accountNo, double amount) {
        Account account = accounts.get(accountNo);
        double updatedBalance = account.getBalance() + amount;
        account.setBalance(updatedBalance);

        return accounts.put(accountNo, account);
    }

    public Account withdrawMoney(String accountNo, double amount) {
        if (amount < 0.0) {
            throw new IllegalArgumentException("Negative amount not allowed!");
        }
        Account account = accounts.get(accountNo);
        if (amount > account.getBalance()){
            throw new RuntimeException("Insufficient funds!");
        }

        double updatedBalance = account.getBalance() - amount;
        account.setBalance(updatedBalance);

        return accounts.put(accountNo, account);
    }
}
