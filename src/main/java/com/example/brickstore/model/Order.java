package com.example.brickstore.model;

import com.example.brickstore.entitiy.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    public Order() {}

    public Order(int amount) {
        this.amount = amount;
        this.orderStatus = OrderStatus.NEW_ORDER;
        this.createdAt = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "order_seq")
    @GenericGenerator(
            name = "order_seq",
            strategy =
                    "com.example.brickstore.model.OrderIdGenerator",
            parameters = {
                    @Parameter(name =
                            OrderIdGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name =
                            OrderIdGenerator.VALUE_PREFIX_PARAMETER, value =
                            "ORDER"),
                    @Parameter(name =
                            OrderIdGenerator.NUMBER_FORMAT_PARAMETER, value =
                            "%05d") })
    private String id;

    @NotNull(message = "Invalid order amount.")
    @Min(1)
    private int amount;

    @NotNull(message = "Invalid order status")
    private OrderStatus orderStatus;


    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

}