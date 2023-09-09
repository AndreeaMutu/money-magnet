package com.example.moneymagnet;

import com.example.moneymagnet.service.Account;
import com.example.moneymagnet.service.AccountService;
import org.junit.jupiter.api.*;

import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountsRetrievalTest {
    private AccountService accountService;
    // @BeforeAll
//    private static final AccountService ACCOUNT_SERVICE = new AccountService();

    @BeforeEach
    void setUp() {
        accountService = new AccountService();
    }

    @DisplayName("No account found when no accounts created")
    @RepeatedTest(10)
    void shouldNotFindAnyAccounts(RepetitionInfo info) {
        //Prepare

        //Execute
        List<Account> allAccounts = accountService.getAll();

        //Verify
        assertEquals(0, allAccounts.size());
    }

    @DisplayName("One account should be found when one account is created")
    @RepeatedTest(10)
    void shouldReturnOneAccountWhenOneAccountIsCreated() {
        //Prepare
        String name = "Test";
        String currency = "USD";
        Double initialBalance = 100.0;
        accountService.createAccount(name, currency, initialBalance);

        //Execute
        List<Account> accountList = accountService.getAll();

        //Verify
        assertEquals(1, accountList.size());
    }

    @DisplayName("Two accounts should be found when 2 are created")
    @RepeatedTest(10)
    void shouldReturnTwoAccountsWhenTwoAccountsAreCreated() {
        //Prepare
        String name = "Test";
        String currency1 = "USD";
        String currency2 = "EUR";
        Double initialBalance1 = 100.0;
        Double initialBalance2 = 50.0;
        Account account1 = accountService.createAccount(name, currency1, initialBalance1);
        Account account2 = accountService.createAccount(name, currency2, initialBalance2);
        List<Account> expectedAccountList = List.of(account1, account2);

        //Execute
        List<Account> accountList = accountService.getAll();

        //Verify
        assertEquals(2, accountList.size());
        assertIterableEquals(expectedAccountList, accountList);
    }

    // TODO Exercise - find by id tests - found, not found, invalid id
}