package com.snapp.fintech.web.rest.swagger;

import com.snapp.fintech.service.dto.CustomAlertDto;
import com.snapp.fintech.service.dto.ExpenseCategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "Report", description = "the report Api")
public interface ReportApi {

    @Operation(summary = "fetch total expense monthly", description = "fetch total expense which stored in data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<Double> getTotalExpenseMonthly(int year, int month);


    @Operation(summary = "fetch total expense by category monthly", description = "fetch total expense by category which stored in data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<List<ExpenseCategoryDto>> getTotalExpenseByCategoryMonthly(int year, int month);


    @Operation(summary = "custom alert", description = "write custom alert for report representation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<List<CustomAlertDto>> customAlert(BigDecimal threshold, int year, int month);
}
