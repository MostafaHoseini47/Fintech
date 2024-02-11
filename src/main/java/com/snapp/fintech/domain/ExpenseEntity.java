package com.snapp.fintech.domain;

import com.snapp.fintech.domain.audit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "EXPENSE")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ExpenseEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date expenseDate;

    private String name;

    private BigDecimal fee;

    private BigDecimal amount;

    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private CategoryEntity category;

    private Boolean isDeleted = Boolean.FALSE;
}
