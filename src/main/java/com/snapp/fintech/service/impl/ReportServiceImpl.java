package com.snapp.fintech.service.impl;

import com.snapp.fintech.repository.ExpenseRepository;
import com.snapp.fintech.service.ReportService;
import com.snapp.fintech.service.dto.CustomAlertDto;
import com.snapp.fintech.service.dto.ExpenseCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ExpenseRepository expenseRepository;


    @Override
    public Double getTotalExpenseForMonth(int year, int month) {
        return expenseRepository.getTotalExpensesForMonth(year, month);
    }

    @Override
    public List<ExpenseCategoryDto> getTotalExpenseByCategoryForMonth(int year, int month) {
        return expenseRepository.getTotalExpenseByCategoryForMonth(year, month);
    }

    @Override
    public List<CustomAlertDto> customAlert(BigDecimal threshold, int year, int month) {
        List<CustomAlertDto> alerts = new ArrayList<>();
        List<ExpenseCategoryDto> expenseCategory = expenseRepository.getTotalExpenseByCategoryForMonth(year, month);
        for (ExpenseCategoryDto expense : expenseCategory) {
            CustomAlertDto alert = new CustomAlertDto();
            if (expense.getTotalAmount().compareTo(threshold) > 0)
                alert.setMessage("You're spending too much on " + expense.getCategory() + "!");
            else
                alert.setMessage("You're spending is perfect");

            alert.setCategory(expense.getCategory());
            alert.setAmountSpent(expense.getTotalAmount());
            alert.setThreshold(threshold);
            alerts.add(alert);
        }
        return alerts;
    }

}
