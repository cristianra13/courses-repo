package com.aws.function.springbootawslambda.repository;

import com.aws.function.springbootawslambda.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class OrderDao
{
    public List<Order> buildOrders()
    {
        return Stream.of(
                new Order(101, "Mobile", 200000, 1),
                new Order(102, "TVs", 20000, 1),
                new Order(103, "Book", 18000, 4),
                new Order(201, "Jeans", 7000, 8),
                new Order(301, "Tables", 16000, 3)
        ).collect(Collectors.toList());
    }
}
