package com.app.customer.service.domain.entities;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name = "regions")
public class Region implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
}