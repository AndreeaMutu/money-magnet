package com.example.moneymagnet.service;

import java.util.Currency;
import java.util.UUID;

public class Account {
    private String accountHolder;
    private String accountNo;
    private Double balance;
    private Currency currency;

    public Account(String accountHolder, Currency currency, Double initialBalance) {
        this.accountHolder = accountHolder;
        this.currency = currency;
        this.balance = initialBalance;
        this.accountNo = UUID.randomUUID().toString();
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountHolder='" + accountHolder + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                '}';
    }
}
