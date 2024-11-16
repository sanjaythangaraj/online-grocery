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
public class CreateItemRequest {

  @NotBlank(message = "Name is mandatory")
  private String name;

  @NotBlank(message = "Category is mandatory")
  private String category;

  @NotNull(message = "Price is mandatory")
  @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
  @Digits(integer = 10, fraction = 2, message = "Price must have two digits after decimal")
  private BigDecimal price;

  @NotNull(message = "Quantity is mandatory")
  @Min(value = 1, message = "Quantity must be at least 1")
  private Integer quantity;
}
