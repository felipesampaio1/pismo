package br.com.pismo.challenge.resource;

import br.com.pismo.challenge.resource.dto.AccountDTO;
import br.com.pismo.challenge.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/accounts")
@AllArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO accountDTO){
        log.debug("REST POST to create account");

        return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long accountId){
        log.debug("REST POST to create account");

        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }
}
