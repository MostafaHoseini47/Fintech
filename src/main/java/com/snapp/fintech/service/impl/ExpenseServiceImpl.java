package com.snapp.fintech.service.impl;

import com.snapp.fintech.domain.ExpenseEntity;
import com.snapp.fintech.domain.UserEntity;
import com.snapp.fintech.exception.NotFoundException;
import com.snapp.fintech.repository.ExpenseRepository;
import com.snapp.fintech.service.CategoryService;
import com.snapp.fintech.service.ExpenseService;
import com.snapp.fintech.service.UserService;
import com.snapp.fintech.service.dto.ExpenseDto;
import com.snapp.fintech.service.dto.ExpenseResponseDto;
import com.snapp.fintech.service.mapper.ExpenseMapper;
import com.snapp.fintech.web.rest.model.ModelResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository repository;

    private final ExpenseMapper mapper;

    private final UserService userService;

    private final CategoryService categoryService;

    @Override
    public ExpenseResponseDto saveExpense(ExpenseDto expenseDto) {
        ExpenseEntity expense = mapper.convertToEntity(expenseDto);
        expense.setTotalPrice(expenseDto.getFee().multiply(expenseDto.getAmount()));
        expense.setExpenseDate(new Date());
        UserEntity currentUser = userService.getCurrentUserAndCalculateStock(expense.getTotalPrice());
        expense.setUser(currentUser);
        expense.setCategory(categoryService.findByIdAndIsDeletedFalse(expenseDto.getCategory()));
        log.info("expense is creating successfully");
        return mapper.convertToDto(repository.save(expense));
    }

    @Override
    public ExpenseResponseDto updateExpense(ExpenseDto expenseDto) {
        ExpenseEntity expense = findExpenseByIdAndIsDeletedFalse(expenseDto.getId());
        if (!Objects.equals(expense.getName(), expenseDto.getName())) {
            expense.setName(expenseDto.getName());
        }
        if (!Objects.equals(expenseDto.getAmount(), expense.getAmount())
                || !Objects.equals(expenseDto.getFee(), expense.getFee())) {
            expense.setAmount(expenseDto.getAmount());
            expense.setFee(expenseDto.getFee());
            expense.setTotalPrice(expenseDto.getFee().multiply(expenseDto.getAmount()));
        }
        if (!Objects.equals(expenseDto.getCategory(), expense.getCategory().getId())) {
            expense.setCategory(categoryService.findByIdAndIsDeletedFalse(expenseDto.getCategory()));
        }
        log.info("expense is updating and change primary information with name:" + expense.getName()+"");
        return mapper.convertToDto(repository.save(expense));
    }

    @Override
    public ExpenseResponseDto findExpenseById(Long id) {
        log.info("Finding expense by ID: {}", id);
        return mapper.convertToDto(findExpenseByIdAndIsDeletedFalse(id));
    }

    @Override
    public Page<ExpenseResponseDto> findAllExpenses(Pageable pageable) {
        Page<ExpenseEntity> expenseEntities = repository.findAllByIsDeletedFalse(pageable);
        return expenseEntities.map(mapper::convertToDto);
    }

    @Override
    public ModelResponse deleteExpense(Long id) {
        ExpenseEntity expense = findExpenseByIdAndIsDeletedFalse(id);
        expense.setIsDeleted(Boolean.TRUE);
        repository.save(expense);
        return ModelResponse.builder().message("expense with id: "+ id +" is deleted").code(200).build();
    }

    public ExpenseEntity findExpenseByIdAndIsDeletedFalse(Long id) {
        return repository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("There isn't expense with id:" + id));
    }
}
