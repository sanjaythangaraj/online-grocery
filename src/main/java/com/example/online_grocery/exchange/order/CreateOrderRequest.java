package com.example.online_grocery.exchange.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class CreateOrderRequest {

  @NotNull(message = "Customer Id is mandatory")
  private Long customerId;

  @NotEmpty(message = "Item Ids list cannot be empty and should contain at least one item")
  private List<Long> itemIds;
}
