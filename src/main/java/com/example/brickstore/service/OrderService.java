package com.example.brickstore.service;

import com.example.brickstore.entitiy.OrderRequest;
import com.example.brickstore.entitiy.OrderResponse;
import com.example.brickstore.model.Order;
import com.example.brickstore.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<OrderResponse> createOrder(OrderRequest orderRequest) {
        Order order = new Order(orderRequest.getAmount());
        order = orderRepository.save(order);
        return new ResponseEntity<>(new OrderResponse(order.getId()), HttpStatus.CREATED);
    }

}
