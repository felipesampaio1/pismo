package br.com.pismo.challenge.service;

import br.com.pismo.challenge.model.Transaction;
import br.com.pismo.challenge.repository.TransactionRepository;
import br.com.pismo.challenge.resource.dto.TransactionDTO;
import br.com.pismo.challenge.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final Mapper mapper;

    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {

        Transaction transaction = mapper.mapTo(transactionDTO, Transaction.class);

        return mapper.mapTo(transactionRepository.save(transaction), TransactionDTO.class);

    }
}
