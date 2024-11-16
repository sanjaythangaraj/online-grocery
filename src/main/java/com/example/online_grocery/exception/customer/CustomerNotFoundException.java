package com.example.online_grocery.exception.customer;

public class CustomerNotFoundException extends RuntimeException{
  public CustomerNotFoundException() {
    super("Customer with the given id not found");
  }

  public CustomerNotFoundException(String message) {
    super(message);
  }
}
