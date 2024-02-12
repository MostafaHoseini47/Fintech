package com.snapp.fintech.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomAlertDto {

    private String message;

    private String category;

    private BigDecimal amountSpent;

    private BigDecimal threshold;
}
