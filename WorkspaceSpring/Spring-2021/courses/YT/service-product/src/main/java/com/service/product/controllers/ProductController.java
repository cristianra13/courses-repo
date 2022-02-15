package com.service.product.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.product.domain.entities.Category;
import com.service.product.domain.entities.Product;
import com.service.product.domain.exception.ErrorMessage;
import com.service.product.domain.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public ResponseEntity<List<Product>> listProducts(@RequestParam(value = "categoryId", required = false) Long categoryId) {
    List<Product> products = new ArrayList<>();
    if (null == categoryId) {
      products = productService.getAllProducts();
      if (products.isEmpty()) {
        return ResponseEntity.noContent().build();
      }
    } else {
      products = productService.findByCategory(Category.builder().id(categoryId).build());
      if (products.isEmpty()) {
        return ResponseEntity.notFound().build();
      }
    }
    return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
    Product product = productService.findById(id);
    if (null == product) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(product);
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result) {
    if (result.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage(result));
    }
    Product productCreated = productService.createProduct(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
    product.setId(id);
    Product productUpdated = productService.updateProduct(product);
    if (null == productUpdated) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(productUpdated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
    Product productDeleted = productService.deleteProduct(id);
    if (productDeleted == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(productDeleted);
  }

  @GetMapping("/{id}/stock")
  public ResponseEntity<Product> updateStockProduct(@PathVariable("id") Long id, @RequestParam("quantity") Double quantity) {
    Product product = productService.updateStock(id, quantity);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(product);
  }

  private String formatMessage(BindingResult result) {
    ObjectMapper mapper = new ObjectMapper();
    String jsonString = "";

    List<Map<String, String>> errors = result.getFieldErrors()
        .stream()
        .map(err -> {
          Map<String, String> error = new HashMap<>();
          error.put(err.getField(), err.getDefaultMessage());
          return error;
        }).collect(Collectors.toList());

    ErrorMessage errorMessage = ErrorMessage.builder()
        .errorCode("01")
        .messages(errors)
        .build();

    try {
      jsonString = mapper.writeValueAsString(errorMessage);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    return jsonString;
  }

}
