package com.example.shop.repo;

import com.example.shop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    @Query("SELECT it FROM CartItem it WHERE it.cart.id=?1 AND it.product.id=?2")
    Optional<CartItem> findByCartAndProduct(Long cartId, Integer productId);

    @Query("SELECT it FROM CartItem it WHERE it.cart.user.username=?1")
    List<CartItem> findByUsername(String username);
}
