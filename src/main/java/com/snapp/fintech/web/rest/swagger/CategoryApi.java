package com.snapp.fintech.web.rest.swagger;

import com.snapp.fintech.service.dto.CategoryDto;
import com.snapp.fintech.web.rest.model.ModelResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

@Tag(name = "Category", description = "the Category Api")
public interface CategoryApi {

    @Operation(summary = "save category", description = "save an category which you want to store in data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<CategoryDto> createCategory(CategoryDto categoryDto);

    @Operation(summary = "update category", description = "update an category which there is in the data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<CategoryDto> updateCategory(CategoryDto categoryDto);

    @Operation(summary = "delete category", description = "delete an category which there is in the data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<ModelResponse> deleteCategory(Long id);

    @Operation(summary = "fetch category by id", description = "fetch an category which there is in the data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<CategoryDto> getCategoryById(Long id);

    @Operation(summary = "fetch all categories", description = "fetch all category which there are in the data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<Page<CategoryDto>> getAllCategories(int page, int size);

}
