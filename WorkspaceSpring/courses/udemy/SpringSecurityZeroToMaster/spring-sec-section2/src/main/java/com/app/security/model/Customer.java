package com.app.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private int id;
  private String name;
  private String email;
  @Column(name = "mobile_number")
  private String mobileNumber;
  @JsonIgnore
  private String pwd;
  private String role;
  @Column(name = "create_dt")
  private String createDt;

  @JsonIgnore
  @OneToMany(mappedBy="customer",fetch=FetchType.EAGER)
  private Set<Authority> authorities;
}
