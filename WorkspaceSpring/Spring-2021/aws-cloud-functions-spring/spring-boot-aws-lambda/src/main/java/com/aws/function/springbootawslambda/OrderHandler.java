package com.aws.function.springbootawslambda;

import com.aws.function.springbootawslambda.domain.Order;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import java.util.List;

public class OrderHandler extends SpringBootRequestHandler<String, List<Order>>
{
    
}
