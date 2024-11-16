package com.example.online_grocery.exchange.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class CustomerResponse {

  private Long id;

  private String name;

  private String email;

  private String address;

  private String phone;

}
