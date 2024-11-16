package com.example.online_grocery.exception.item;

public class ItemNotFoundException extends RuntimeException {

  public ItemNotFoundException() {
    super("Item with given id not found");
  }

  public ItemNotFoundException(String message) {
    super(message);
  }
}
