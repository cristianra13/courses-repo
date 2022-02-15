package com.app.testing.models;

import com.app.testing.exception.InsufficientMoneyException;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String person;
    private BigDecimal balance;

    public void debit(BigDecimal amount) {
        BigDecimal newBalance = this.balance.subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientMoneyException("the account does not have sufficient money");
        }
        this.balance = newBalance;
    }

    public void credit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

}
