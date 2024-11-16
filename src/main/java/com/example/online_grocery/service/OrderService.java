package com.example.online_grocery.service;

import com.example.online_grocery.data.CustomerEntity;
import com.example.online_grocery.data.ItemEntity;
import com.example.online_grocery.data.OrderEntity;
import com.example.online_grocery.exception.customer.CustomerExistsException;
import com.example.online_grocery.exception.customer.CustomerNotFoundException;
import com.example.online_grocery.exception.order.OrderNotFoundException;
import com.example.online_grocery.exchange.customer.CreateCustomerRequest;
import com.example.online_grocery.exchange.customer.CustomerResponse;
import com.example.online_grocery.exchange.customer.UpdateCustomerRequest;
import com.example.online_grocery.exchange.item.ItemResponse;
import com.example.online_grocery.exchange.order.CreateOrderRequest;
import com.example.online_grocery.exchange.order.OrderResponse;
import com.example.online_grocery.exchange.order.UpdateOrderRequest;
import com.example.online_grocery.repository.CustomerRepository;
import com.example.online_grocery.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private ItemService itemService;

  public List<OrderResponse> findAll() {
    List<OrderEntity> orderEntities = orderRepository.findAll();
    return orderEntities.stream()
        .map(this::mapToOrderResponse)
        .toList();
  }

  public OrderResponse findById(Long id) {
    return orderRepository.findById(id)
        .map(this::mapToOrderResponse)
        .orElseThrow(OrderNotFoundException::new);
  }

  public OrderResponse save(CreateOrderRequest createOrderRequest) {
    CustomerEntity customerEntity = modelMapper.map(customerService.findById(createOrderRequest.getCustomerId()), CustomerEntity.class);
    List<ItemEntity> itemEntities = createOrderRequest.getItemIds()
        .stream()
        .map(itemId -> itemService.findById(itemId))
        .map(itemResponse -> modelMapper.map(itemResponse, ItemEntity.class))
        .toList();
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setCustomerEntity(customerEntity);
    orderEntity.setItemEntities(itemEntities);
    orderEntity.setOrderDate(LocalDate.now());
    OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
    return mapToOrderResponse(savedOrderEntity);
  }

  public OrderResponse updateOrder(Long id, UpdateOrderRequest request) {
    OrderEntity updatedOrderEntity = orderRepository.save(mapToOrderEntity(request, findById(id)));
    return mapToOrderResponse(updatedOrderEntity);
  }

  public void deleteById(Long id) {
    findById(id);
    orderRepository.deleteById(id);
  }

  private OrderEntity mapToOrderEntity(UpdateOrderRequest request, OrderResponse response) {
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setId(response.getId());
    orderEntity.setOrderDate(response.getOrderDate());

    CustomerResponse customerResponse;
    if (request.getCustomerId() != null) {
      customerResponse = customerService.findById(request.getCustomerId());
    } else {
      customerResponse = customerService.findById(response.getCustomerId());

    }
    orderEntity.setCustomerEntity(modelMapper.map(customerResponse, CustomerEntity.class));

    if (request.getItemIds() != null) {
      List<ItemResponse> itemResponses = request.getItemIds().stream()
          .map(itemId -> itemService.findById(itemId))
          .toList();

      orderEntity.setTotalPrice(getTotalPrice(itemResponses));
      orderEntity.setItemEntities(
          itemResponses.stream()
              .map(itemResponse -> modelMapper.map(itemResponse, ItemEntity.class))
              .toList()
      );

    } else {
      orderEntity.setItemEntities(response.getItemResponses().stream()
          .map(itemResponse -> modelMapper.map(itemResponse, ItemEntity.class))
          .toList()
      );
    }

    return orderEntity;
  }

  private OrderResponse mapToOrderResponse(OrderEntity orderEntity) {
    OrderResponse orderResponse = new OrderResponse();
    orderResponse.setId(orderEntity.getId());
    orderResponse.setOrderDate(orderEntity.getOrderDate());
    orderResponse.setCustomerId(orderEntity.getCustomerEntity().getId());
    List<ItemResponse> itemResponses = orderEntity.getItemEntities()
        .stream()
        .map(itemEntity -> modelMapper.map(itemEntity, ItemResponse.class))
        .toList();

    orderResponse.setItemResponses(itemResponses);
    orderResponse.setTotalPrice(getTotalPrice(itemResponses));

    return orderResponse;
  }

  private static BigDecimal getTotalPrice(List<ItemResponse> itemResponses) {
    return itemResponses.stream()
        .map(itemResponse -> itemResponse.getPrice().multiply(BigDecimal.valueOf(itemResponse.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add)
        .setScale(2, RoundingMode.HALF_UP);
  }
}
