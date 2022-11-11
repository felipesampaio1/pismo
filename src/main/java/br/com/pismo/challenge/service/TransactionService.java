package br.com.pismo.challenge.service;

import br.com.pismo.challenge.enumeration.OperationTypeEnum;
import br.com.pismo.challenge.exception.OperationalTypeException;
import br.com.pismo.challenge.model.Account;
import br.com.pismo.challenge.model.Transaction;
import br.com.pismo.challenge.repository.TransactionRepository;
import br.com.pismo.challenge.resource.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {

    private final AccountService accountService;
    private final TransactionRepository transactionRepository;


    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {

        Account account = findByIdIfExists(transactionDTO.getAccountId());

        OperationTypeEnum operationType = validateAndGetOperationalType(transactionDTO.getOperationTypeId());

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(operationType.isNegate() ? transactionDTO.getAmount().negate() : transactionDTO.getAmount())
                .operationType(operationType)
                .build();

        accountService.setNewLimit(account, transaction);

        return TransactionDTO.fill(transactionRepository.save(transaction));
    }

    private OperationTypeEnum validateAndGetOperationalType(Integer id) {

        return OperationTypeEnum.byId(id)
                .orElseThrow(() -> new OperationalTypeException("Operation Type não localizado"));
    }

    private Account findByIdIfExists(Long accountId) {
        return accountService.findAccountByIdIfExits(accountId);
    }
}
