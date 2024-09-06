package com.example.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="cartitems")
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy =
        GenerationType.IDENTITY)
    Long id;

    Integer quantity;

    @ManyToOne @JoinColumn(name="productid")
    Product product;

    @JsonIgnore
    @ManyToOne @JoinColumn(name="cartid")
    Cart cart;

    public Long getSubTotal(){
        if(product != null) {
            return (long) quantity * (int) product.getPrice();
        }else{
            return 0l;
        }
    }
}
