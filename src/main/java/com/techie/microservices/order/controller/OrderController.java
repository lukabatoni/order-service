package com.techie.microservices.order.controller;

import com.techie.microservices.order.dto.OrderRequestDto;
import com.techie.microservices.order.repository.OrderRepository;
import com.techie.microservices.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
    orderService.placeOrder(orderRequestDto);
  }
}
