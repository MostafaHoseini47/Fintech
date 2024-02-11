package com.snapp.fintech.web.rest;

import com.snapp.fintech.config.constant.PathUrlConstants;
import com.snapp.fintech.config.metrics.ApiMetrics;
import com.snapp.fintech.service.ExpenseService;
import com.snapp.fintech.service.dto.ExpenseDto;
import com.snapp.fintech.service.dto.ExpenseResponseDto;
import com.snapp.fintech.web.rest.model.ModelResponse;
import com.snapp.fintech.web.rest.swagger.ExpenseApi;
import io.micrometer.core.annotation.Timed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PathUrlConstants.EXPENSE_CONTROLLER)
@RequiredArgsConstructor
public class ExpenseController implements ExpenseApi {

    private final ExpenseService service;

    private final ApiMetrics apiMetrics;

    @Override
    @PostMapping
    @Timed
    public ResponseEntity<ExpenseResponseDto> saveExpense(@Valid @RequestBody ExpenseDto expenseDto) {
        ExpenseResponseDto expenseResponse = service.saveExpense(expenseDto);
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseResponse);
    }

    @Override
    @PutMapping
    @Timed
    public ResponseEntity<ExpenseResponseDto> updateExpense(@RequestBody ExpenseDto expenseDto) {
        ExpenseResponseDto expenseResponse = service.updateExpense(expenseDto);
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(expenseResponse);
    }

    @Override
    @DeleteMapping("/{id}")
    @Timed
    public ResponseEntity<ModelResponse> deleteExpense(@PathVariable("id") Long id) {
        ModelResponse modelResponse = service.deleteExpense(id);
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(modelResponse);
    }

    @Override
    @GetMapping("/{id}")
    @Timed
    public ResponseEntity<ExpenseResponseDto> getExpenseById(@PathVariable("id") Long id) {
        ExpenseResponseDto expenseResponse = service.findExpenseById(id);
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(expenseResponse);
    }

    @Override
    @GetMapping
    @Timed
    public ResponseEntity<Page<ExpenseResponseDto>> getAllExpenses(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        Page<ExpenseResponseDto> expenseResponses = service.findAllExpenses(PageRequest.of(page, size));
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(expenseResponses);
    }
}
