package com.example.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="categories")
public class Category {
    @Id String id;
    String name;
}
