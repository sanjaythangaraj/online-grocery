package com.example.online_grocery.controller;


import com.example.online_grocery.exchange.customer.CreateCustomerRequest;
import com.example.online_grocery.exchange.customer.CustomerResponse;
import com.example.online_grocery.exchange.customer.UpdateCustomerRequest;
import com.example.online_grocery.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @GetMapping
  public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
    List<CustomerResponse> responses = customerService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(responses);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id) {
    CustomerResponse response = customerService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping()
  public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CreateCustomerRequest createCustomerRequest) {
    CustomerResponse response = customerService.save(createCustomerRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CustomerResponse> updateCustomer(
      @PathVariable Long id,
      @RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) {

    CustomerResponse response = customerService.updateCustomer(id, updateCustomerRequest);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable Long id) {
    customerService.deleteById(id);
    return ResponseEntity.noContent().build();
  }


}
