package br.com.pismo.challenge.service;

import br.com.pismo.challenge.exception.OperationalTypeException;
import br.com.pismo.challenge.model.Transaction;
import br.com.pismo.challenge.repository.TransactionRepository;
import br.com.pismo.challenge.resource.dto.TransactionDTO;
import br.com.pismo.challenge.util.DataTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private AccountService accountService;

    @Mock
    private TransactionRepository transactionRepository;

    private final DataTest dataTest = new DataTest();

    @Test
    void shouldCreateTransactionWithSuccessful(){

        when(accountService.findAccountByIdIfExits(anyLong()))
                .thenReturn(dataTest.getAccountSaved());

        when(transactionRepository.save(any(Transaction.class)))
                .thenReturn(dataTest.getTransaction());

        TransactionDTO result = transactionService
                .createTransaction(dataTest.getTransactionDTO());

        verify(accountService, times(1)).findAccountByIdIfExits(anyLong());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        assertNotNull(result);

    }

    @Test
    void shouldThrowsExceptionWhenOperationTypeNotExists(){

        when(accountService.findAccountByIdIfExits(anyLong()))
                .thenReturn(dataTest.getAccountSaved());

        TransactionDTO transactionDTO = dataTest.getTransactionDTO();
        transactionDTO.setOperationTypeId(-1);

        assertThrows(OperationalTypeException.class, () -> transactionService.createTransaction(transactionDTO));
        verify(accountService, times(1)).findAccountByIdIfExits(anyLong());
        verify(transactionRepository, times(0)).save(any(Transaction.class));

    }
}
