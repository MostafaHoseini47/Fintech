package com.snapp.fintech.web.rest.swagger;

import com.snapp.fintech.service.dto.ExpenseDto;
import com.snapp.fintech.service.dto.ExpenseResponseDto;
import com.snapp.fintech.web.rest.model.ModelResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

@Tag(name = "Expense", description = "the expense Api")
public interface ExpenseApi {

    @Operation(summary = "save expense", description = "save an expense which you want to store in data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<ExpenseResponseDto> saveExpense(ExpenseDto expenseDto);

    @Operation(summary = "update expense", description = "update an expense which there is in the data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<ExpenseResponseDto> updateExpense(ExpenseDto expenseDto);

    @Operation(summary = "delete expense", description = "delete an expense which there is in the data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<ModelResponse> deleteExpense(Long id);

    @Operation(summary = "fetch expense by id", description = "fetch an expense which there is in the data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<ExpenseResponseDto> getExpenseById(Long id);

    @Operation(summary = "fetch all expenses", description = "fetch all expenses which there are in the data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<Page<ExpenseResponseDto>> getAllExpenses(int page, int size);
}
