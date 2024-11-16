package com.example.online_grocery.service;

import com.example.online_grocery.data.ItemEntity;
import com.example.online_grocery.exception.item.ItemNotFoundException;
import com.example.online_grocery.exchange.item.CreateItemRequest;
import com.example.online_grocery.exchange.item.ItemResponse;
import com.example.online_grocery.exchange.item.UpdateItemRequest;
import com.example.online_grocery.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private ModelMapper modelMapper;

  public List<ItemResponse> findAll() {
    List<ItemEntity> itemEntities = itemRepository.findAll();
    return itemEntities.stream()
        .map(itemEntity -> {
          return modelMapper.map(itemEntity, ItemResponse.class);
        })
        .toList();
  }

  public ItemResponse findById(Long id) {
    return itemRepository.findById(id)
        .map(itemEntity -> modelMapper.map(itemEntity, ItemResponse.class))
        .orElseThrow(ItemNotFoundException::new);
  }

  public ItemResponse save(CreateItemRequest request) {
    ItemEntity itemEntity = modelMapper.map(request, ItemEntity.class);
    ItemEntity savedItemEntity = itemRepository.save(itemEntity);
    return modelMapper.map(savedItemEntity, ItemResponse.class);
  }

  public ItemResponse updateCustomer(Long id, UpdateItemRequest request) {
    ItemEntity updatedItemEntity = itemRepository.save(mapToItemEntity(request, findById(id)));
    return modelMapper.map(updatedItemEntity, ItemResponse.class);
  }

  public void deleteById(Long id) {
    findById(id);
    itemRepository.deleteById(id);
  }

  private ItemEntity mapToItemEntity(UpdateItemRequest request, ItemResponse response) {
    ItemEntity itemEntity = modelMapper.map(response, ItemEntity.class);
    if (request.getName() != null) itemEntity.setName(request.getName());
    if (request.getCategory() != null) itemEntity.setCategory(request.getCategory());
    if (request.getPrice() != null) itemEntity.setPrice(request.getPrice());
    if (request.getQuantity() != null) itemEntity.setQuantity(request.getQuantity());
    return itemEntity;
  }
}
