package com.example.moneymagnet;

import com.example.moneymagnet.service.Account;
import com.example.moneymagnet.service.AccountService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddMoneyToAccountTest {
    private final AccountService accountService = new AccountService();
    @ParameterizedTest(name = "{index} - Balance should be {0} when adding {0} to a new account")
    @ValueSource(doubles = {100.0, 200.0, 65.5})
    void accountBalanceShouldBeUpdated_whenMoneyIsAdded(double amount) {
        Account account = accountService.createAccount("A", "USD");
        Account updatedAccount = accountService.addMoney(account.getAccountNo(), amount);
        assertEquals(amount, updatedAccount.getBalance());
    }

//    @ParameterizedTest(name = "{index} - Balance should be {2} when adding {1} to account with initial balance of {0}")
//    @CsvSource({"100.0,10.0,110.0", "25.3,11.41,36.71"})
    @ParameterizedTest(name = "{index} - {2} when {0} and {1}")
    @CsvFileSource(resources = "/test.csv", useHeadersInDisplayName = true)
    void accountBalanceShouldBeUpdated_whenMoneyIsAdded(double initialBalance, double amountToAdd, double expectedBalance) {
        Account account = accountService.createAccount("A", "USD", initialBalance);
        Account updatedAccount = accountService.addMoney(account.getAccountNo(), amountToAdd);
        assertEquals(expectedBalance, updatedAccount.getBalance());
    }

}
