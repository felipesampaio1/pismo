package br.com.pismo.challenge.resource;

import br.com.pismo.challenge.resource.dto.TransactionDTO;
import br.com.pismo.challenge.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/transactions")
@AllArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;


    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody @Valid TransactionDTO transactionDTO){
        log.debug("REST POST to create a transaction");

        return ResponseEntity.ok(transactionService.createTransaction(transactionDTO));

    }
}
