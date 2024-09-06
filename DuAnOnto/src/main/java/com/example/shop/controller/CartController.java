package com.example.shop.controller;

import com.example.shop.dto.CartDTO;
import com.example.shop.dto.CartItemDTO;
import com.example.shop.entity.*;
import com.example.shop.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
public class CartController {
    @Autowired UserRepo userRepo;
    @Autowired CartRepo cartRepo;
    @Autowired ProductRepo productRepo;
    @Autowired CartItemRepo cartItemRepo;

    @PostMapping("/add-cart-item")
    public ResponseEntity<?> addCartItem(@RequestBody CartItemDTO body){
        Cart cart = cartRepo.findByUsername(body.getUsername()).orElse(null);
        if(cart == null){
            cart = new Cart();
            cart.setUser(userRepo.findById(body.getUsername()).orElse(null));
            cart.setCreatedate(new Date());
            cartRepo.save(cart);
        }
        CartItem cartItem = cartItemRepo.findByCartAndProduct(cart.getId(), body.getProductId())
                                .orElse(null);
        if(cartItem == null){
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(productRepo.findById(body.getProductId()).orElse(null));
            cartItem.setQuantity(body.getQuantity());
        }else{
            cartItem.setQuantity(cartItem.getQuantity() + body.getQuantity());
        }

        cartItemRepo.save(cartItem);
        return ResponseEntity.ok(cartItem);
    }

    @PutMapping("/update-cart-item")
    public ResponseEntity<?> updateCartItem(@RequestBody CartItemDTO body){
        return null; // TODO
    }

    @DeleteMapping("/delete-cart-item/{id}")
    public void deleteCartItem(@PathVariable Long id){
        cartItemRepo.deleteById(id);
    }

    @GetMapping("/list-cart-item")
    public List<CartItem> listCartItem(@RequestParam String username){
        return cartItemRepo.findByUsername(username);
    }

    @Autowired OrderRepo orderRepo;
    @Autowired OrderDetailRepo orderDetailRepo;

    @PostMapping("/complete-order")
    public void completeOrder(@RequestBody CartDTO body){
        User user = userRepo.findById(body.getUsername()).orElse(null);
        Order order = new Order();
        order.setUser(user);
        order.setAddress(body.getAddress());
        order.setCreatedate(new Date());
        order.setStatus(Order.Status.PENDING);
        orderRepo.save(order);
        List<CartItem> cartItems = cartItemRepo.findByUsername(body.getUsername());
        List<OrderDetail> orderDetails = new ArrayList<>();
        for(CartItem cartItem : cartItems){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setPrice(cartItem.getProduct().getPrice());
            orderDetail.setOrder(order);
            orderDetails.add(orderDetail);
        }
        orderDetailRepo.saveAll(orderDetails);
        cartItemRepo.deleteAll(cartItems);
    }
}
