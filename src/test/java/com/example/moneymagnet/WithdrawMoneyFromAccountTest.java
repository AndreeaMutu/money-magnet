package com.example.moneymagnet;

import com.example.moneymagnet.service.Account;
import com.example.moneymagnet.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class WithdrawMoneyFromAccountTest {

    private AccountService accountService;

    @BeforeEach
    void setupAccount() {
        accountService = new AccountService();
    }


    @ParameterizedTest
    @MethodSource("withdrawFailureArguments")
    void shouldThrowException_whenWithdrawIsNotPossible(double initialBalance, double amountToWithdraw, String expectedException) {
        Account account = accountService.createAccount("A", "EUR", initialBalance);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> accountService.withdrawMoney(account.getAccountNo(), amountToWithdraw));

        Assertions.assertEquals(expectedException, exception.getMessage());
    }

    static Stream<Arguments> shouldWithdrawMoney_whenInitialBalanceIsProvided() {
        return Stream.of(
                Arguments.of(1.01, 0.01, 1.0),
                Arguments.of(100.0, 50.0, 50.0),
                Arguments.of(100.0, 0.0, 100.0),
                Arguments.of(2.01, 1.0, 1.01)
        );
    }

    @ParameterizedTest
//    @MethodSource("withdrawSuccessArguments")
    @MethodSource
    void shouldWithdrawMoney_whenInitialBalanceIsProvided(double initialBalance, double amountToWithdraw, double expectedBalance) {
        Account account = accountService.createAccount("A", "EUR", initialBalance);
        Account updatedAccount = accountService.withdrawMoney(account.getAccountNo(), amountToWithdraw);
        Assertions.assertEquals(expectedBalance, updatedAccount.getBalance());
    }

//    static Stream<Arguments> withdrawSuccessArguments() {
//        return Stream.of(
//                Arguments.of(1.01, 0.01, 1.0),
//                Arguments.of(100.0, 50.0, 50.0),
//                Arguments.of(100.0, 0.0, 100.0),
//                Arguments.of(2.01, 1.0, 1.01),
//                Arguments.of(2.01, 2.01, 0.0)
//        );
//    }

    static Stream<Arguments> withdrawFailureArguments() {
        return Stream.of(
                Arguments.of(1.01, 2.01, "Insufficient funds!"),
                Arguments.of(100.0, -50.0, "Negative amount not allowed!")
        );
    }
}
