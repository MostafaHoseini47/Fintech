package com.snapp.fintech.service;

import com.snapp.fintech.domain.ExpenseEntity;
import com.snapp.fintech.service.dto.ExpenseDto;
import com.snapp.fintech.service.dto.ExpenseResponseDto;
import com.snapp.fintech.web.rest.model.ModelResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {

    ExpenseResponseDto saveExpense(ExpenseDto expenseDto);

    ExpenseResponseDto updateExpense(ExpenseDto expenseDto);

    ExpenseResponseDto findExpenseById(Long id);

    Page<ExpenseResponseDto> findAllExpenses(Pageable pageable);

    ModelResponse deleteExpense(Long id);

    ExpenseEntity findExpenseByIdAndIsDeletedFalse(Long id);

}
