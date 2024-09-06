package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Orderdetails")
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(
      strategy= GenerationType.IDENTITY
    )
    Long id;

    Integer price;
    Integer quantity;

    @ManyToOne @JoinColumn(name="Productid")
    Product product;

    @ManyToOne @JoinColumn(name="Orderid")
    Order order;

}
