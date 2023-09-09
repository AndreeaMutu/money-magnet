package com.example.moneymagnet;

import com.example.moneymagnet.service.Account;
import com.example.moneymagnet.service.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Account creation - account should ")
class AccountCreationTest {
    private final AccountService accountService = new AccountService();

    @DisplayName("be created when name and currency are provided")
    @Test
    void shouldCreateAccountGivenNameAndCurrency() {
        //Prepare
        String name = "Test";
        String currency = "USD";

        //Execute
        Account account = accountService.createAccount(name, currency);

        //Verify
        assertNotNull(account);
        assertEquals(name, account.getAccountHolder());
        assertEquals(Currency.getInstance(currency), account.getCurrency());
        assertEquals(0.0, account.getBalance());
    }

    @DisplayName("be created when name, currency and initial balance are provided")
    @Test
    void shouldCreateAccountGivenNameCurrencyAndInitialBalance() {
        //Prepare
        String name = "Test";
        String currency = "USD";
        Double initialBalance = 100.0;

        //Execute
        Account account = accountService.createAccount(name, currency, initialBalance);

        //Verify
        assertAll("Account details", () -> {
            assertNotNull(account.getAccountNo());
            assertEquals(name, account.getAccountHolder());
            assertEquals(Currency.getInstance(currency), account.getCurrency());
            assertEquals(initialBalance, account.getBalance());
        });
    }

    @DisplayName("not be created when name is not provided. Exception should be thrown.")
    @Test
    void shouldNotCreateAccountWhenNameIsMissing() {
        //Prepare
        String name = null;
        String currency = "USD";

        //Execute and Verify
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> accountService.createAccount(name, currency));
        assertEquals("Client name must be provided!", illegalArgumentException.getMessage());
    }
}