package com.example.online_grocery.exchange.order;

import com.example.online_grocery.validation.NotEmptyIfPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class UpdateOrderRequest {

  private Long customerId;

  @NotEmptyIfPresent(message = "If provided, the item IDs list cannot be empty and should contain at least one item")
  private List<Long> itemIds;
}
