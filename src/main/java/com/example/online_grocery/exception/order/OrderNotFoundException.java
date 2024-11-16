package com.example.online_grocery.exception.order;

public class OrderNotFoundException extends RuntimeException{

  public OrderNotFoundException() {
    super("Order with given id not found");
  }

  public OrderNotFoundException(String message) {
    super(message);
  }
}
