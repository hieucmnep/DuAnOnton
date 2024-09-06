package com.example.shop.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    Integer productId;
    Integer quantity;
    String username;
}
