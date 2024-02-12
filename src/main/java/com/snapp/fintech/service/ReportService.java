package com.snapp.fintech.service;

import com.snapp.fintech.service.dto.CustomAlertDto;
import com.snapp.fintech.service.dto.ExpenseCategoryDto;

import java.math.BigDecimal;
import java.util.List;

public interface ReportService {

    Double getTotalExpenseForMonth(int year, int month);
    List<ExpenseCategoryDto> getTotalExpenseByCategoryForMonth(int year, int month);

    List<CustomAlertDto> customAlert(BigDecimal threshold, int year, int month);

}
