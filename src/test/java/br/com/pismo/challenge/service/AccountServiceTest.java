package br.com.pismo.challenge.service;

import br.com.pismo.challenge.exception.InsufficientFundsException;
import br.com.pismo.challenge.model.Account;
import br.com.pismo.challenge.model.Transaction;
import br.com.pismo.challenge.repository.AccountRepository;
import br.com.pismo.challenge.util.DataTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    private final DataTest dataTest = new DataTest();

    @Test
    void shouldthrowsExceptionWhenInsufficientFundsOccurs(){

        Account accountSavedWithFunds = dataTest.getAccountSavedWithFunds();
        Transaction transaction = dataTest.getTransaction();
        transaction.setAmount(new BigDecimal("10000").negate());

        assertThrows(InsufficientFundsException.class,
                () -> accountService.setNewLimit(accountSavedWithFunds, transaction));

    }

}
