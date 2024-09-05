package com.banorte.course.controller;

import com.banorte.course.MovementRepository;
import com.banorte.course.ms.system.adapter.application.domain.model.entity.Account;
import com.banorte.course.ms.system.adapter.in.rest.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account/")
public class AccountController {
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @GetMapping
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }
    @GetMapping("{id}")
    public Optional<Account> getAccountById(@PathVariable Long id){
        return accountRepository.findById(id);
    }

    @PostMapping("create")
    public Account createAccount(@RequestBody Account account){
        return accountRepository.save(account);
    }

    @PutMapping("update")
    public Account updateAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteAccount(@RequestBody Account account) {
        accountRepository.delete(account);
        return new ResponseEntity(HttpStatus.OK);
    }
}
