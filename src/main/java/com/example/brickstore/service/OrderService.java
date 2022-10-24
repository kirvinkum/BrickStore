package com.example.brickstore.service;

import com.example.brickstore.entitiy.ListOrderResponse;
import com.example.brickstore.entitiy.OrderRequest;
import com.example.brickstore.entitiy.OrderResponse;
import com.example.brickstore.exception.CustomException;
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

    public ListOrderResponse listOrder(String orderRef) {

        //validate
        if(orderRef.length() < 5 || !orderRef.toUpperCase().contains("ORDER")){
            throw new CustomException("Invalid order refrence");
        }

        //get order
        Order order = orderRepository.findById(orderRef);

        //Return nothing if order not found
        if(order == null){
            return null;
        }

        //or return proper response
        return new ListOrderResponse(order.getId(), order.getAmount());
    }
}
