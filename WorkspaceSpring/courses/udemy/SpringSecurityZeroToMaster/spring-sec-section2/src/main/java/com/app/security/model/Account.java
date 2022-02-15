package com.app.security.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Account {
  @Id
  @Column(name = "account_number")
  private long accountNumber;

  @Column(name = "customer_id")
  private int customerId;

  @Column(name = "account_type")
  private String accountType;

  @Column(name = "branch_address")
  private String branchAddress;

  @Column(name = "create_dt")
  private String createDt;
}
