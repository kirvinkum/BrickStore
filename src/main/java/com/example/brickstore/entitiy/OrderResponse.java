package com.example.brickstore.entitiy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {

    @JsonProperty("order_ref")
    private String orderRef;

    public OrderResponse(){}

    public OrderResponse(String orderRef) {
        this.orderRef = orderRef;
    }
}
