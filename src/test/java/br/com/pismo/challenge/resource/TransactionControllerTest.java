package br.com.pismo.challenge.resource;

import br.com.pismo.challenge.AbstractIntegrationTest;
import br.com.pismo.challenge.model.Transaction;
import br.com.pismo.challenge.repository.TransactionRepository;
import br.com.pismo.challenge.service.AccountService;
import br.com.pismo.challenge.util.DataTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TransactionControllerTest extends AbstractIntegrationTest {

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private AccountService accountService;

    private final DataTest dataTest = new DataTest();

    @Test
    void shouldCreateTransactionWithSuccessful() throws Exception {

        when(accountService.findAccountByIdIfExits(anyLong()))
                .thenReturn(dataTest.getAccountSaved());

        when(transactionRepository.save(any(Transaction.class)))
                .thenReturn(dataTest.getTransaction());


        mockMvc.perform(post("/v1/transactions")
                        .contentType(APPLICATION_JSON)
                        .content(dataTest.asJsonString(dataTest.getTransactionDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account_id").value("1"))
                .andExpect(jsonPath("$.operation_type_id").value("1"))
                .andExpect(jsonPath("$.amount").value("100"));

    }

}
