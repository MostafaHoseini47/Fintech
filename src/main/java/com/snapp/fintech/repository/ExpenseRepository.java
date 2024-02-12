package com.snapp.fintech.repository;

import com.snapp.fintech.domain.ExpenseEntity;
import com.snapp.fintech.service.dto.ExpenseCategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    Optional<ExpenseEntity> findByIdAndIsDeletedFalse(Long id);


    Page<ExpenseEntity> findAllByIsDeletedFalse(Pageable pageable);

    @Query("select sum(e.totalPrice)from ExpenseEntity e where year(e.createdDate) = ?1 and month(e.createdDate) = ?2")
    Double getTotalExpensesForMonth(int year, int month);

    @Query("select new com.snapp.fintech.service.dto.ExpenseCategoryDto(e.category.name, sum(e.totalPrice)) from ExpenseEntity e" +
            " where year(e.createdDate)= ?1 and month(e.createdDate)= ?2 group by e.category")
    List<ExpenseCategoryDto> getTotalExpenseByCategoryForMonth(int year, int month);

}
