package br.com.pismo.challenge.resource;

import br.com.pismo.challenge.AbstractIntegrationTest;
import br.com.pismo.challenge.exception.ErrorEnum;
import br.com.pismo.challenge.model.Account;
import br.com.pismo.challenge.repository.AccountRepository;
import br.com.pismo.challenge.util.DataTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends AbstractIntegrationTest {

    @MockBean
    private AccountRepository accountRepository;

    private final DataTest dataTest = new DataTest();

    @Test
    void shouldCreateAccountWithSuccessful() throws Exception {

        when(accountRepository.save(any(Account.class)))
                .thenReturn(dataTest.getAccountSaved());

        mockMvc.perform(post("/v1/accounts")
                        .contentType(APPLICATION_JSON)
                        .content(dataTest.asJsonString(dataTest.getAccountDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.document_number").value("12345678900"));

    }

    @Test
    void shouldGetAccountWithSuccessful() throws Exception {

        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.of(dataTest.getAccountSaved()));

        mockMvc.perform(get("/v1/accounts/{accountId}", "1")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.document_number").value("12345678900"));

    }

    @Test
    void shouldGetAccountWithNotFound() throws Exception {

        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/v1/accounts/{accountId}", "1")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value(ErrorEnum.ACCOUNT_NOT_FOUND.name()))
                .andExpect(jsonPath("$.message").value(ErrorEnum.ACCOUNT_NOT_FOUND.getMessage()));

    }

}