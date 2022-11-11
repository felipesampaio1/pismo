package br.com.pismo.challenge.service;

import br.com.pismo.challenge.exception.InsufficientFundsException;
import br.com.pismo.challenge.model.Account;
import br.com.pismo.challenge.model.Transaction;
import br.com.pismo.challenge.repository.AccountRepository;
import br.com.pismo.challenge.resource.dto.AccountDTO;
import br.com.pismo.challenge.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final Mapper mapper;


    public AccountDTO createAccount(AccountDTO accountDTO){

        Account account = mapper.mapTo(accountDTO, Account.class);

        return mapper.mapTo(accountRepository.save(account), AccountDTO.class);
    }


    public AccountDTO getAccountById(Long accountId) {

        Account account = getAccount(accountId);

        return mapper.mapTo(account, AccountDTO.class);
    }

    public Account findAccountByIdIfExits(Long accountId) {

        return getAccount(accountId);
    }

    private Account getAccount(Long accountId) {

        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Conta não localizada"));
    }

    public void setNewLimit(Account account, Transaction transaction) {

        BigDecimal newLimit = account.getAvailableCreditLimit().add(transaction.getAmount());

        if (newLimit.signum() > 0){
            account.setAvailableCreditLimit(newLimit);
            accountRepository.save(account);
        }

        else {
            throw new InsufficientFundsException("Conta não possui saldo para realizar a transação.");
        }

    }
}
