package com.snapp.fintech.repository;

import com.snapp.fintech.domain.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByIdAndIsDeletedFalse(Long id);

    Page<CategoryEntity> findAllByIsDeletedFalse(Pageable pageable);
}
