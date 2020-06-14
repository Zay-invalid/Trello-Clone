package com.zay.trelloClone.controllers;

import com.zay.trelloClone.exception.ResourceNotFoundException;
import com.zay.trelloClone.models.Account;
import com.zay.trelloClone.repositories.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public List<Account> list() {
        return accountRepository.findAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<Account> getAccountByUsername(@PathVariable(value = "username") String username)
        throws ResourceNotFoundException {
        Account account =  accountRepository.findById(username).orElseThrow(()->new ResourceNotFoundException("Account not found on:: "+ username));
        return ResponseEntity.ok().body(account);
    }

    @PostMapping
    public Account create(@RequestBody Account account) {
        return accountRepository.saveAndFlush(account);
    }

    @DeleteMapping("{id}")
    public Map<String,Boolean> deleteAccount(@PathVariable(value = "username") String username) throws Exception {
        Account account = accountRepository.findById(username)
                .orElseThrow(()->new ResourceNotFoundException("Account not found on ::" + username));
        accountRepository.delete(account);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }

    @PutMapping("/{username}")
    public Account update(@PathVariable String username, @RequestBody Account account) throws Exception{
        Account oldAccount = accountRepository.findById(username)
                .orElseThrow(()->new ResourceNotFoundException("Account not found on ::" + username));
        BeanUtils.copyProperties(account, oldAccount, "verified" );
        return accountRepository.saveAndFlush(oldAccount);
    }
}
