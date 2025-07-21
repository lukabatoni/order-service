package com.techie.microservices.order.service;

import com.techie.microservices.order.client.InventoryClient;
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
  private final InventoryClient inventoryClient;

  private static final String PRODUCT_WITH_SKUCODE = "Product with skucode ";
  private static final String IS_NOT_IN_STOCK = " is not in stock";

  public void placeOrder(OrderRequestDto orderRequestDto) {
    var isProductInStock = inventoryClient.isInStock(
        orderRequestDto.skuCode(), orderRequestDto.quantity());
    if (isProductInStock){
      Order order = new Order();
      order.setOrderNumber(UUID.randomUUID().toString());
      order.setSkuCode(orderRequestDto.skuCode());
      order.setPrice(orderRequestDto.price());
      order.setQuantity(orderRequestDto.quantity());
      orderRepository.save(order);
    }else {
      throw new RuntimeException(PRODUCT_WITH_SKUCODE + orderRequestDto.skuCode() + IS_NOT_IN_STOCK);
    }

  }
}
