package com.example.brickstore.entitiy;

public enum OrderStatus {

    NEW_ORDER("NEW_ORDER"),
    DISPATCHED("DISPATCHED");

    private final String status;

    OrderStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
