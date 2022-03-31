package com.app.security.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "account_transactions")
public class AccountTransaction {

  @Id
  @Column(name = "transaction_id")
  private String transactionId;

  @Column(name = "account_number")
  private long accountNumber;

  @Column(name = "customer_id")
  private int customerId;

  @Column(name = "transaction_dt")
  private Date transactionDt;

  @Column(name = "transaction_summary")
  private String transactionSummary;

  @Column(name = "transaction_type")
  private String transactionType;

  @Column(name = "transaction_amt")
  private int transactionAmt;

  @Column(name = "closing_balance")
  private int closingBalance;

  @Column(name = "create_dt")
  private String createDt;
}
