package com.example.shop.controller;

import com.example.shop.entity.Category;
import com.example.shop.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class CategoryController {
    @Autowired
    CategoryRepo repo;
    // localhost:8080/list-category
    @GetMapping("/list-category")
    public List<Category> getAll(){return repo.findAll();}

    // localhost:8080/create-category
    @PostMapping("/create-category")
    public Category create(@RequestBody Category category){
        repo.save(category);
        return category;
    }

    // localhost:8080/delete-category/{id}
    @DeleteMapping("/delete-category/{id}")
    public void delete(@PathVariable String id){
        repo.deleteById(id);
    }
}
