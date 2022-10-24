package com.example.brickstore.entitiy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderFullFillRequest {

    @JsonProperty("order_ref")
    @NotNull(message = "Invalid order refrence")
    @NotEmpty(message = "Invalid order refrence")
    private String orderRef;


    @NotNull(message = "Invalid order status")
    private OrderStatus status;
}