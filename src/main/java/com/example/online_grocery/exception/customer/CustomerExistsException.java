package com.example.online_grocery.exception.customer;

public class CustomerExistsException extends RuntimeException{

  public CustomerExistsException(String message) {
    super(message);
  }

  public CustomerExistsException() {
    super("Customer with given email or phone already exists");
  }
}
