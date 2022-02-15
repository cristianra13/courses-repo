package com.service.product.domain.services;

import com.service.product.domain.entities.Category;
import com.service.product.domain.entities.Product;

import java.util.List;

public interface ProductService {

  List<Product> getAllProducts();

  Product findById(Long id);

  Product createProduct(Product product);

  Product updateProduct(Product product);

  Product deleteProduct(Long id);

  List<Product> findByCategory(Category category);

  Product updateStock(Long id, Double quantity);
}
