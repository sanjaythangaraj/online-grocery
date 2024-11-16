package com.example.online_grocery.controller;

import com.example.online_grocery.exchange.item.CreateItemRequest;
import com.example.online_grocery.exchange.item.ItemResponse;
import com.example.online_grocery.exchange.item.UpdateItemRequest;
import com.example.online_grocery.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

  @Autowired
  private ItemService itemService;

  @GetMapping
  public ResponseEntity<List<ItemResponse>> getAllItems() {
    List<ItemResponse> responses = itemService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(responses);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ItemResponse> getItem(@PathVariable Long id) {
    ItemResponse response = itemService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping()
  public ResponseEntity<ItemResponse> createItem(@RequestBody @Valid CreateItemRequest createItemRequest) {
    ItemResponse response = itemService.save(createItemRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ItemResponse> updateItem(
      @PathVariable Long id,
      @RequestBody @Valid UpdateItemRequest updateItemRequest) {

    ItemResponse response = itemService.updateCustomer(id, updateItemRequest);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ItemResponse> deleteItem(@PathVariable Long id) {
    itemService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
