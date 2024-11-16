package com.example.online_grocery.exchange.item;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class UpdateItemRequest {

  private String name;

  private String category;

  @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
  @Digits(integer = 10, fraction = 2, message = "Price must have two digits after decimal")
  private BigDecimal price;

  @Min(value = 1, message = "Quantity must be at least 1")
  private Integer quantity;
}
