package com.example.brickstore.controller;

import com.example.brickstore.entitiy.*;
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

    /**
     * list order with a valid order reference
     * @param order_ref
     * @return order refrence id and amount.
     * @exception BAD_REQUEST(400) Invalid request body
     * @exception NOT_ACCEPTABLE(406) Invalid params
     * @exception BAD_REQUEST(400) Invalid order ref
     */
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ListOrderResponse list(@RequestParam(name = "order_ref") String OrderRef){
        return orderService.listOrder(OrderRef);
    }


    /**
     * update order amount with a valid order reference
     * @param order_ref
     * @return order refrence id and amount.
     * @exception BAD_REQUEST(400) Invalid request body
     * @exception NOT_ACCEPTABLE(406) Invalid params
     * @exception BAD_REQUEST(400) Invalid order ref
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderResponse updateAmount(@Valid @RequestBody OrderUpdateRequest orderUpdateRequest){
        return orderService.updateOrder(orderUpdateRequest);
    }

    /**
     * fullfill order amount with a valid order reference
     * @param order_ref
     * @return order refrence id and amount.
     * @exception BAD_REQUEST(400) Invalid request body
     * @exception NOT_ACCEPTABLE(406) Invalid params
     * @exception BAD_REQUEST(400) Invalid order ref
     */
    @ResponseBody
    @RequestMapping(value = "fullfill", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderResponse fullFill(@Valid @RequestBody OrderFullFillRequest orderFullFillRequest){
        return orderService.fullFillOrder(orderFullFillRequest);
    }

}
