package com.example.online_grocery.exchange.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class CreateCustomerRequest {

  @NotBlank(message = "Name is mandatory")
  private String name;

  @Email(message = "Email should be valid")
  @NotBlank(message = "Email is mandatory")
  private String email;

  private String address;

  @NotBlank(message = "Phone number is mandatory")
  @Pattern(regexp="(^$|[0-9]{10})", message = "Phone number must be 10 digits")
  private String phone;

}
