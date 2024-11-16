package com.example.online_grocery.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class OrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate orderDate;

  private BigDecimal totalPrice;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerEntity customerEntity;

  @ManyToMany
  @JoinTable(
      name = "order_item",
      joinColumns = @JoinColumn(name = "order_id"),
      inverseJoinColumns = @JoinColumn(name = "item_id"))
  private List<ItemEntity> itemEntities;
}
