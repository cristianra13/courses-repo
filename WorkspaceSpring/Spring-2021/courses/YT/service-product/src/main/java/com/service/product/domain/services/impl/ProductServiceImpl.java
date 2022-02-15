package com.service.product.domain.services.impl;

import com.service.product.domain.entities.Category;
import com.service.product.domain.entities.Product;
import com.service.product.domain.services.ProductService;
import com.service.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product findById(Long id) {
    return productRepository.findById(id).orElse(null);
  }

  @Override
  public Product createProduct(Product product) {
    product.setStatus("CREATED");
    product.setCreateAt(new Date());
    return productRepository.save(product);
  }

  @Override
  public Product updateProduct(Product product) {
    Optional<Product> productdb = productRepository.findById(product.getId());
    if (productdb.isEmpty()) {
      return null;
    }
    productdb.get().setName(product.getName());
    productdb.get().setDescription(product.getDescription());
    productdb.get().setCategory(product.getCategory());
    productdb.get().setPrice(product.getPrice());

    return productRepository.save(productdb.get());
  }

  @Override
  public Product deleteProduct(Long id) {
    Optional<Product> productdb = productRepository.findById(id);
    if (productdb.isEmpty()) {
      return null;
    }
    productdb.get().setStatus("DELETED");
    return productRepository.save(productdb.get());
  }

  @Override
  public List<Product> findByCategory(Category category) {
    return productRepository.findByCategory(category);
  }

  @Override
  public Product updateStock(Long id, Double quantity) {
    Optional<Product> productdb = productRepository.findById(id);
    if (productdb.isEmpty()) {
      return null;
    }
    Double stock = productdb.get().getStock() + quantity;
    productdb.get().setStock(stock);

    return productRepository.save(productdb.get());
  }
}
