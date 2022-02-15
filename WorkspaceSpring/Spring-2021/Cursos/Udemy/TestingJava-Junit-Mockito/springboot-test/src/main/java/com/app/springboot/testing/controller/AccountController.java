package com.app.springboot.testing.controller;

import com.app.springboot.testing.domain.models.Account;
import com.app.springboot.testing.domain.models.dto.TransferDto;
import com.app.springboot.testing.domain.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/")
    @ResponseStatus(OK)
    public ResponseEntity<?> findAll() {
        List<Account> allAccounts = accountService.findAll();
        if (allAccounts == null) {
            return ResponseEntity.status(500).build();
        }

        if (allAccounts.size() == 0) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(allAccounts);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ResponseEntity<?> detailAccount(@PathVariable Long id) {

        Account account = null;
        try {
            account = accountService.findById(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(account);
    }

    @PostMapping("/")
    @ResponseStatus(CREATED)
    public ResponseEntity<?> saveAccount(@RequestBody Account account) {
        Account accountSaved = accountService.save(account);

        return ResponseEntity.status(201).body(accountSaved);
    }

    @PostMapping("/transfer")
    @ResponseStatus()
    public ResponseEntity<?> transfer(@RequestBody TransferDto transferDto) {
        accountService.tranfer(
                transferDto.getOriginAccountId(),
                transferDto.getDestinationAccountId(),
                transferDto.getAmount(),
                transferDto.getBankId());
        Map<String, Object> response = new HashMap<>();
        response.put("date", LocalDate.now());
        response.put("status", "OK");
        response.put("message", "transfer successful");
        response.put("transaction", transferDto);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        accountService.deleteById(id);
    }

}
