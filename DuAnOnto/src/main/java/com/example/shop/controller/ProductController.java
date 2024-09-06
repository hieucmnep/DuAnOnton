package com.example.shop.controller;

import com.example.shop.entity.Product;
import com.example.shop.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
public class ProductController {
    @Autowired
    ProductRepo repo;

    // localhost:8080/product-detail/1
    @GetMapping("/product-detail/{id}")
    public Product getById(@PathVariable int id){
        return repo.findById(id).orElse(null);
    }

    // localhost:8080/search-product?minPrice=10000000&maxPrice=15000000&keyword=IPhone&categoryId=IP
    @GetMapping("/search-product")
    public List<Product> search(
        @RequestParam(defaultValue="") String keyword, @RequestParam(defaultValue="0") int minPrice,
        @RequestParam Optional<Integer> maxPrice, @RequestParam Optional<String> categoryId
    ){
        keyword = "%" + keyword + "%";
        return repo.search( keyword, minPrice, maxPrice.orElse(null), categoryId.orElse(null));
    }
}
