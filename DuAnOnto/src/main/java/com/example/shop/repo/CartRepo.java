package com.example.shop.repo;

import com.example.shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.user.username=?1")
    Optional<Cart> findByUsername(String username);
}
