package com.app.springboot.testing.domain.models.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDto {
    private Long originAccountId;
    private Long destinationAccountId;
    private BigDecimal amount;
    private Long bankId;
}
