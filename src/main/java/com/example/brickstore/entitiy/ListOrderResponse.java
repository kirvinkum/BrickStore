package com.example.brickstore.entitiy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListOrderResponse {

    @JsonProperty("order_ref")
    private String orderRef;

    @JsonProperty("amount")
    private int amount;

    public ListOrderResponse(){}

    public ListOrderResponse(String orderRef, int amount) {
        this.orderRef = orderRef;
        this.amount = amount;
    }
}