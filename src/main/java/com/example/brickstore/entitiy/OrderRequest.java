package com.example.brickstore.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class OrderRequest {

    @NotNull(message = "Invalid order amount")
    @Min(1)
    private int amount;

    public OrderRequest() {}

    public OrderRequest(int amount) {
        this.amount = amount;
    }
}
