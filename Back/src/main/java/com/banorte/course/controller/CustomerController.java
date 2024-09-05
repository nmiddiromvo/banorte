package com.banorte.course.controller;

import com.banorte.course.ms.system.adapter.application.domain.model.entity.Customer;
import com.banorte.course.ms.system.adapter.in.rest.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer/")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getAccounts(){
        return customerRepository.findAll();
    }
    @GetMapping("{id}")
    public Optional<Customer> getAccountById(@PathVariable Long id){
        return customerRepository.findById(id);
    }

    @PostMapping("create")
    public Customer createAccount(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @PutMapping("update")
    public Customer updateAccount(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteAccount(@RequestBody Customer customer) {
        customerRepository.delete(customer);
        return new ResponseEntity(HttpStatus.OK);
    }
}
