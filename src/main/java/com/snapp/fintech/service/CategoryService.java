package com.snapp.fintech.service;

import com.snapp.fintech.domain.CategoryEntity;
import com.snapp.fintech.service.dto.CategoryDto;
import com.snapp.fintech.web.rest.model.ModelResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto dto);

    CategoryDto updateCategory(CategoryDto dto);

    CategoryDto findCategoryById(Long id);

    Page<CategoryDto> findAllCategories(Pageable pageable);

    ModelResponse deleteCategory(Long id);

    CategoryEntity findByIdAndIsDeletedFalse(Long id);


}
