package com.service.product.domain.services.impl;

import com.service.product.domain.entities.Category;
import com.service.product.domain.entities.Product;
import com.service.product.domain.services.ProductService;
import com.service.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
class ProductServiceImplTest {

  @Mock
  private ProductRepository productRepository;

  private ProductService productService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    productService = new ProductServiceImpl(productRepository);

    Product computer = Product.builder()
        .name("Computer")
        .category(Category.builder().id(1L).build())
        .stock(Double.parseDouble("5"))
        .price(Double.parseDouble("5400.99"))
        .createAt(new Date())
        .build();

    Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(computer));
    Mockito.when(productRepository.save(computer)).thenReturn(computer);
  }

  @Test
  void findproductComputerByIdTest() {
    Product computer = productService.findById(1L);
    Assertions.assertThat(computer.getName()).isEqualTo("Computer");
  }

  @Test
  void updateStockProductTest() {
    Product newStock = productService.updateStock(1l, Double.parseDouble("8"));
    Assertions.assertThat(newStock.getStock()).isEqualTo(13);
  }

}