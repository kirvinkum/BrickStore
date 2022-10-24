package com.example.brickstore.controller;

import com.example.brickstore.entitiy.OrderRequest;
import com.example.brickstore.entitiy.OrderResponse;
import com.example.brickstore.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/order", produces= MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Create order for a number of bricks is submitted
     * @param amount - can be extended with product id etc
     * @return order refrence id.
     * @exception BAD_REQUEST(400) Invalid request body
     * @exception NOT_ACCEPTABLE(406) Invalid params
     */
    @ResponseBody
    @RequestMapping(value = "create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }

}
