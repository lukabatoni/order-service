package com.techie.microservices.order.service;

import com.techie.microservices.order.dto.OrderRequestDto;
import com.techie.microservices.order.model.Order;
import com.techie.microservices.order.repository.OrderRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;

  public void placeOrder(OrderRequestDto orderRequestDto) {
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());
    order.setSkuCode(orderRequestDto.skuCode());
    order.setPrice(orderRequestDto.price());
    order.setQuantity(orderRequestDto.quantity());
    orderRepository.save(order);
  }
}
