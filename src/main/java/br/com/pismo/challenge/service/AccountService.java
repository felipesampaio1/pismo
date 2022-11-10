package br.com.pismo.challenge.service;

import br.com.pismo.challenge.model.Account;
import br.com.pismo.challenge.repository.AccountRepository;
import br.com.pismo.challenge.resource.dto.AccountDTO;
import br.com.pismo.challenge.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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
}
