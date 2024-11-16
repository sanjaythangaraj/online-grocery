package com.example.online_grocery.repository;

import com.example.online_grocery.data.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

  Optional<CustomerEntity> findOneByEmailOrPhone(String email, String phone);
}
