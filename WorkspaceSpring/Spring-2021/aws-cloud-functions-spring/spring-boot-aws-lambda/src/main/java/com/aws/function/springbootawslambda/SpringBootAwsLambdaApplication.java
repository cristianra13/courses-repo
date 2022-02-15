package com.aws.function.springbootawslambda;

import com.aws.function.springbootawslambda.domain.Order;
import com.aws.function.springbootawslambda.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringBootAwsLambdaApplication {

    @Autowired
    private OrderDao orderDao;

    @Bean
    public Supplier<List<Order>> orders()
    {
        return () -> orderDao.buildOrders();
    }

    @Bean
    public Function<String, List<Order>> findOrderByName()
    {
        return (name) -> orderDao.buildOrders().stream().filter(order -> order.getName().equals(name)).collect(Collectors.toList());
    }

    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootAwsLambdaApplication.class, args);
    }
}
