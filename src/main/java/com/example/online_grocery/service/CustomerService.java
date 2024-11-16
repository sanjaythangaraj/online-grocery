package com.example.online_grocery.service;

import com.example.online_grocery.data.CustomerEntity;
import com.example.online_grocery.exception.customer.CustomerExistsException;
import com.example.online_grocery.exception.customer.CustomerNotFoundException;
import com.example.online_grocery.exchange.customer.CreateCustomerRequest;
import com.example.online_grocery.exchange.customer.CustomerResponse;
import com.example.online_grocery.exchange.customer.UpdateCustomerRequest;
import com.example.online_grocery.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private ModelMapper modelMapper;

  public List<CustomerResponse> findAll() {
    List<CustomerEntity> customerEntities = customerRepository.findAll();
    return customerEntities.stream()
        .map(customerEntity -> {
          return modelMapper.map(customerEntity, CustomerResponse.class);
        })
        .toList();
  }

  public CustomerResponse findById(Long id) {
    return customerRepository.findById(id)
        .map(customerEntity -> modelMapper.map(customerEntity, CustomerResponse.class))
        .orElseThrow(CustomerNotFoundException::new);
  }

  public CustomerResponse save(CreateCustomerRequest createCustomerRequest) {

    if (customerRepository
        .findOneByEmailOrPhone(createCustomerRequest.getEmail(), createCustomerRequest.getPhone())
        .isPresent()
    ) throw new CustomerExistsException();

    CustomerEntity customerEntity = modelMapper.map(createCustomerRequest, CustomerEntity.class);
    CustomerEntity savedcustomerEntity = customerRepository.save(customerEntity);
    return modelMapper.map(savedcustomerEntity, CustomerResponse.class);
  }

  public CustomerResponse updateCustomer(Long id, UpdateCustomerRequest request) {
    CustomerEntity updatedCustomerEntity = customerRepository.save(mapToCustomerEntity(request, findById(id)));
    return modelMapper.map(updatedCustomerEntity, CustomerResponse.class);
  }

  public void deleteById(Long id) {
    findById(id);
    customerRepository.deleteById(id);
  }

  private CustomerEntity mapToCustomerEntity(UpdateCustomerRequest request, CustomerResponse response) {
    CustomerEntity customerEntity = modelMapper.map(response, CustomerEntity.class);
    if (request.getName() != null) customerEntity.setName(request.getName());
    if (request.getEmail() != null) customerEntity.setEmail(request.getEmail());
    if (request.getAddress() != null) customerEntity.setAddress(request.getAddress());
    if (request.getPhone() != null) customerEntity.setPhone(request.getPhone());
    return customerEntity;
  }
}
