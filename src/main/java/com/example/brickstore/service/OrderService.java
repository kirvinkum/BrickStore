package com.example.brickstore.service;

import com.example.brickstore.entitiy.*;
import com.example.brickstore.exception.CustomException;
import com.example.brickstore.exception.OrderNotFoundException;
import com.example.brickstore.model.Order;
import com.example.brickstore.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    @Transactional
    public OrderResponse updateOrder(OrderUpdateRequest orderUpdateRequest) {

        Order order = orderRepository.findById(orderUpdateRequest.getOrderRef());

        if(order == null){
            throw new OrderNotFoundException("Order not found.");
        }

        if(order.getOrderStatus() == OrderStatus.DISPATCHED){
            throw new CustomException("Order update not allowed.");
        }

        order.setAmount(orderUpdateRequest.getAmount());
        order = orderRepository.save(order);

        //return order ref
        return new OrderResponse(order.getId());
    }

    @Transactional
    public OrderResponse fullFillOrder(OrderFullFillRequest orderFullFillRequest) {

        Order order = orderRepository.findById(orderFullFillRequest.getOrderRef());

        if(order == null){
            throw new OrderNotFoundException("Order not found.");
        }

        if(order.getOrderStatus() == orderFullFillRequest.getStatus()){
            //already up to date
            return new OrderResponse(order.getId());
        }

        order.setOrderStatus(orderFullFillRequest.getStatus());
        order = orderRepository.save(order);

        //return order ref
        return new OrderResponse(order.getId());

    }


}
