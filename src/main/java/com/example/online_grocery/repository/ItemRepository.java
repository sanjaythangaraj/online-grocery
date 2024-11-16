package com.example.online_grocery.repository;

import com.example.online_grocery.data.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

}
