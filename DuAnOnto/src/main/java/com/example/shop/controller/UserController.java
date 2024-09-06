package com.example.shop.controller;

import com.example.shop.entity.User;
import com.example.shop.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("*")
@RestController
public class UserController {
    @Autowired UserRepo userRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(
        @RequestBody User user
    ){
        User dbUser = userRepo.findById(
            user.getUsername()
        ).orElse(null);
        if(dbUser == null ||
            !dbUser.getPassword()
            .equals(user.getPassword())
        ){
            return ResponseEntity
                    .status(401)
                    .build();
        }
        return ResponseEntity
                .ok()
                .build();
    }
}
