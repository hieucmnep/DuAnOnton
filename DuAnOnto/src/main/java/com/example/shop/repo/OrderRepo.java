package com.example.shop.repo;

import com.example.shop.dto.RevenueByDateDTO;
import com.example.shop.dto.RevenueByProductDTO;
import com.example.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    @Query("SELECT DISTINCT(o) FROM Order o JOIN OrderDetail od ON od.order.id=o.id WHERE " +
            "(o.user.fullname LIKE ?1 OR od.product.name LIKE ?1) " +
            "AND (?2 IS NULL OR o.createdate >= ?2) " +
            "AND (?3 IS NULL OR o.createdate <=?3) " +
            "AND (?4 IS NULL OR o.status=?4)")
    List<Order> search(String keyword, Date fromDate, Date toDate, Integer status);

    @Query(value = "select p.name AS product, SUM(quantity*od.price) AS revenue\n" +
            "FROM OrderDetails od\n" +
            "JOIN Products p ON p.id=od.Productid\n" +
            "JOIN Orders o ON o.id=od.Orderid\n" +
            "WHERE createdate between ?1 AND ?2\n" +
            "GROUP BY Productid, p.name\n" +
            "ORDER BY revenue DESC", nativeQuery = true)
    List<RevenueByProductDTO> reportByProduct(Date fromDate, Date toDate);

    @Query(value = "select createdate as date, SUM(quantity*od.price) AS revenue\n" +
            "FROM OrderDetails od\n" +
            "JOIN Products p ON p.id=od.Productid\n" +
            "JOIN Orders o ON o.id=od.Orderid\n" +
            "WHERE createdate between ?1 AND ?2\n" +
            "GROUP BY createdate", nativeQuery = true)
    List<RevenueByDateDTO> reportByDate(Date fromDate, Date toDate);
}
