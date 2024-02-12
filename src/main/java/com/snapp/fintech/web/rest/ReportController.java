package com.snapp.fintech.web.rest;

import com.snapp.fintech.config.constant.PathUrlConstants;
import com.snapp.fintech.config.metrics.ApiMetrics;
import com.snapp.fintech.service.ReportService;
import com.snapp.fintech.service.dto.CustomAlertDto;
import com.snapp.fintech.service.dto.ExpenseCategoryDto;
import com.snapp.fintech.web.rest.swagger.ReportApi;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(PathUrlConstants.REPORT_CONTROLLER)
@RequiredArgsConstructor
public class ReportController implements ReportApi {

    private final ReportService reportService;

    private final ApiMetrics apiMetrics;

    @Override
    @GetMapping("/monthly-total-expense")
    @Timed
    public ResponseEntity<Double> getTotalExpenseMonthly(@RequestParam int year, @RequestParam int month) {
        Double totalExpense = reportService.getTotalExpenseForMonth(year, month);
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(totalExpense);
    }

    @Override
    @GetMapping("/total-expense-by-category-monthly")
    @Timed
    public ResponseEntity<List<ExpenseCategoryDto>> getTotalExpenseByCategoryMonthly(@RequestParam int year, @RequestParam int month) {
        List<ExpenseCategoryDto> totalExpenseByCategory = reportService.getTotalExpenseByCategoryForMonth(year, month);
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(totalExpenseByCategory);
    }

    @Override
    @GetMapping("/custom-alert")
    @Timed
    public ResponseEntity<List<CustomAlertDto>> customAlert(@RequestParam BigDecimal threshold,
                                                            @RequestParam int year, @RequestParam int month) {
        List<CustomAlertDto> customAlerts = reportService.customAlert(threshold, year, month);
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(customAlerts);
    }
}
