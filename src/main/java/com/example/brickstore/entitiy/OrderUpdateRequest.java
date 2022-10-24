package com.example.brickstore.entitiy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderUpdateRequest {

    @JsonProperty("order_ref")
    @NotNull(message = "Invalid order refrence")
    @NotEmpty(message = "Invalid order refrence")
    private String orderRef;


    @NotNull(message = "Invalid order amount")
    @Min(1)
    private int amount;
}
