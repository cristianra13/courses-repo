package com.service.product.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // Nos permite hacer nuevas instancias de la entidad
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;                
  @NotEmpty(message = "name no must be empty")
  private String name;
  private String description;
  @Positive(message = "stock must be greater than zero")
  private Double stock;
  private Double price;
  private String status;
  @Column(name = "create_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createAt;
  @NotNull(message = "category not must be empty")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  private Category category;
}
