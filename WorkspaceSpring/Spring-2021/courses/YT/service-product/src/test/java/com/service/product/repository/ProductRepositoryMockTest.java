package com.service.product.repository;

import com.service.product.domain.entities.Category;
import com.service.product.domain.entities.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

/*
  testeamos un JPA
 */
@DataJpaTest
public class ProductRepositoryMockTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  void findByCategoryTest() {
    Product product01 = Product.builder()
        .name("Computer")
        .category(Category.builder().id(1L).build())
        .description("")
        .stock(Double.parseDouble("10"))
        .price(Double.parseDouble("1240.99"))
        .status("Created")
        .createAt(new Date())
        .build();

    productRepository.save(product01);

    List<Product> products = productRepository.findByCategory(product01.getCategory());
    Assertions.assertThat(products.size()).isEqualTo(3);
  }

}
