package com.example.shop.repo;

import com.example.shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo
    extends JpaRepository<Category,String> {
}
