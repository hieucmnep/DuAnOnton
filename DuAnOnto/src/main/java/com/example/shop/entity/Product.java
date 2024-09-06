package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(
        strategy=GenerationType.IDENTITY
    )
    Integer id;
    String name;
    String image;
    Integer price;
    Boolean available;

    @ManyToOne
    @JoinColumn(name="Categoryid")
    Category category;
}
