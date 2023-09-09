package com.example.moneymagnet.model;

public record CreateAccountRequest(String name, String currency, Double balance) {
}
