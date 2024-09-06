package com.example.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="carts")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy
            = GenerationType.IDENTITY)
    Long id;

    Date createdate;

    @JsonIgnore
    @ManyToOne @JoinColumn(name="username")
    User user;
}
