package com.example.online_grocery.exchange.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class ItemResponse {
  private Long id;
  private String name;
  private String category;
  private BigDecimal price;
  private int quantity;
}
