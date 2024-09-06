package com.example.shop.repo;

import com.example.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Query(
      "SELECT p FROM Product p WHERE p.name LIKE ?1 AND p.price>=?2 AND (?3 IS NULL OR p.price <= ?3) AND (?4 IS NULL OR p.category.id=?4)"
    )
    List<Product> search(String keyword, int minPrice, Integer maxPrice, String categoryId);
}
