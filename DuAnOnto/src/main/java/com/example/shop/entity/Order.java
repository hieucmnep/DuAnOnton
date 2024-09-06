package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="Orders")
@Data
public class Order {
    @Id
    @GeneratedValue(
      strategy=GenerationType.IDENTITY
    )
    Long id;

    @ManyToOne
    @JoinColumn(name="username")
    User user;

    String address;

    @Temporal(TemporalType.DATE)
    Date createdate;
    Date deliver;
    int status;
    public static class Status{
        public final static int PENDING = 0;
        public final static int DELIVERED = 1;
        public final static int CANCELLED = 2;
    }
}
