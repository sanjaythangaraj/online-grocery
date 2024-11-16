package com.example.online_grocery.exchange.order;


import com.example.online_grocery.exchange.item.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class OrderResponse {
  private Long id;
  private LocalDate orderDate;
  private BigDecimal totalPrice;
  private Long customerId;
  private List<ItemResponse> itemResponses;
}
