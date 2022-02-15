package com.app.security.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "cards")
public class Cards {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int cardId;

  @Column(name = "customer_id")
  private int customerId;

  @Column(name = "card_number")
  private String cardNumber;

  @Column(name = "card_type")
  private String cardYype;

  @Column(name = "total_limit")
  private int totalLimit;

  @Column(name = "amount_used")
  private int amountUsed;

  @Column(name = "available_amount")
  private int availableAmount;

  @Column(name = "create_at")
  private Date createAt;
}
