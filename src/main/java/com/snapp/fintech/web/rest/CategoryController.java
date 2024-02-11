package com.snapp.fintech.web.rest;

import com.snapp.fintech.config.constant.PathUrlConstants;
import com.snapp.fintech.config.metrics.ApiMetrics;
import com.snapp.fintech.service.CategoryService;
import com.snapp.fintech.service.dto.CategoryDto;
import com.snapp.fintech.web.rest.model.ModelResponse;
import com.snapp.fintech.web.rest.swagger.CategoryApi;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PathUrlConstants.CATEGORY_CONTROLLER)
@RequiredArgsConstructor
public class CategoryController implements CategoryApi {

    private final CategoryService service;

    private final ApiMetrics apiMetrics;

    @Override
    @PostMapping
    @Timed
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto category = service.createCategory(categoryDto);
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @Override
    @PutMapping
    @Timed
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto category = service.updateCategory(categoryDto);
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(category);
    }

    @Override
    @DeleteMapping("/{id}")
    @Timed
    public ResponseEntity<ModelResponse> deleteCategory(@PathVariable("id") Long id) {
        ModelResponse modelResponse = service.deleteCategory(id);
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(modelResponse);
    }

    @Override
    @GetMapping("/{id}")
    @Timed
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long id) {
        CategoryDto category = service.findCategoryById(id);
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(category);
    }

    @Override
    @GetMapping
    @Timed
    public ResponseEntity<Page<CategoryDto>> getAllCategories(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        Page<CategoryDto> categories = service.findAllCategories(PageRequest.of(page, size));
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(categories);
    }

}
