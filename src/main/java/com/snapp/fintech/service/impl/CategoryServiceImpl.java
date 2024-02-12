package com.snapp.fintech.service.impl;

import com.snapp.fintech.domain.CategoryEntity;
import com.snapp.fintech.exception.NotFoundException;
import com.snapp.fintech.repository.CategoryRepository;
import com.snapp.fintech.service.CategoryService;
import com.snapp.fintech.service.dto.CategoryDto;
import com.snapp.fintech.service.mapper.CategoryMapper;
import com.snapp.fintech.web.rest.model.ModelResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private final CategoryMapper mapper;

    @Override
    public CategoryDto createCategory(CategoryDto dto) {
        log.info("category is creating successfully");
        return mapper.convertToDto(repository.save(mapper.convertToEntity(dto)));
    }

    @Override
    public CategoryDto updateCategory(CategoryDto dto) {
        CategoryEntity category = findByIdAndIsDeletedFalse(dto.getId());
        BeanUtils.copyProperties(mapper.convertToEntity(dto), category);
        log.info("category is updating and change primary information with name:" + category.getName()+"");
        return mapper.convertToDto(repository.save(category));
    }

    @Override
    public CategoryDto findCategoryById(Long id) {
        log.info("Finding category by ID: {}", id);
        return mapper.convertToDto(findByIdAndIsDeletedFalse(id));
    }

    @Override
    public Page<CategoryDto> findAllCategories(Pageable pageable) {
        Page<CategoryEntity> categories = repository.findAllByIsDeletedFalse(pageable);
        return categories.map(mapper::convertToDto);
    }

    @Override
    public ModelResponse deleteCategory(Long id) {
        CategoryEntity category = findByIdAndIsDeletedFalse(id);
        category.setIsDeleted(Boolean.TRUE);
        repository.save(category);
        return ModelResponse.builder().message("category with id: "+ id +" is deleted").code(200).build();
    }

    public CategoryEntity findByIdAndIsDeletedFalse(Long id) {
        return repository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("There isn't category with id:" + id));
    }
}
