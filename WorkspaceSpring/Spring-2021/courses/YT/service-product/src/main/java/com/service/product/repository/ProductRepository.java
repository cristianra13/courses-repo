package com.service.product.repository;

import com.service.product.domain.entities.Category;
import com.service.product.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findByCategory(Category category);
}
