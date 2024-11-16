package com.example.online_grocery.controller;

import com.example.online_grocery.exchange.order.CreateOrderRequest;
import com.example.online_grocery.exchange.order.OrderResponse;
import com.example.online_grocery.exchange.order.UpdateOrderRequest;
import com.example.online_grocery.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @GetMapping
  public ResponseEntity<List<OrderResponse>> getAllOrders() {
    List<OrderResponse> responses = orderService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(responses);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
    OrderResponse response = orderService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping()
  public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
    OrderResponse response = orderService.save(createOrderRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<OrderResponse> updateOrder(
      @PathVariable Long id,
      @RequestBody @Valid UpdateOrderRequest updateOrderRequest) {

    OrderResponse response = orderService.updateOrder(id, updateOrderRequest);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<OrderResponse> deleteOrder(@PathVariable Long id) {
    orderService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
