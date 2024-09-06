package com.example.shop.controller;

import com.example.shop.dto.RevenueByDateDTO;
import com.example.shop.dto.RevenueByProductDTO;
import com.example.shop.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.shop.repo.OrderRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class OrderController {
    @Autowired OrderRepo orderRepo;
    Date parseDate(String st){
        if(st == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(st);
        } catch (ParseException e) {
            return null;
        }
    }
    @GetMapping("/search-order")
    public List<Order> search(
        @RequestParam(defaultValue="") String keyword,
        @RequestParam Optional<String> fromDate,
        @RequestParam Optional<String> toDate,
        @RequestParam Optional<Integer> status
    ){
        return orderRepo.search(
            "%" + keyword + "%",
            parseDate(fromDate.orElse(null)),
            parseDate(toDate.orElse(null)),
            status.orElse(null)
        );
    }

    @GetMapping("/report-by-product")
    public List<RevenueByProductDTO> reportByProduct(
        @RequestParam String fromDate,
        @RequestParam String toDate
    ){
        return orderRepo.reportByProduct(
            parseDate(fromDate), parseDate(toDate)
        );
    }

    @GetMapping("/report-by-date")
    public List<RevenueByDateDTO> reportByDate(
        @RequestParam String fromDate, @RequestParam String toDate
    ){
        return orderRepo.reportByDate(
            parseDate(fromDate), parseDate(toDate)
        );
    }
}
