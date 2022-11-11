package br.com.pismo.challenge.util;

import br.com.pismo.challenge.enumeration.OperationTypeEnum;
import br.com.pismo.challenge.model.Account;
import br.com.pismo.challenge.model.Transaction;
import br.com.pismo.challenge.resource.dto.AccountDTO;
import br.com.pismo.challenge.resource.dto.TransactionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;


public class DataTest {

    public AccountDTO getAccountDTO(){
        return AccountDTO.builder()
                .documentNumber("12345678900")
                .build();
    }

    public Transaction getTransaction(){
        return Transaction.builder()
                .account(getAccountSaved())
                .operationType(OperationTypeEnum.COMPRA_A_VISTA)
                .amount(new BigDecimal("100"))
                .build();
    }

    public TransactionDTO getTransactionDTO(){
        return TransactionDTO.builder()
                .accountId(1L)
                .operationTypeId(1)
                .amount(new BigDecimal("100"))
                .build();
    }

    public Account getAccountSaved(){
        return Account.builder()
                .id(1L)
                .documentNumber("12345678900")
                .build();
    }

    public Account getAccountSavedWithFunds(){
        return Account.builder()
                .id(1L)
                .documentNumber("12345678900")
                .availableCreditLimit(new BigDecimal("5000"))
                .build();
    }



    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
