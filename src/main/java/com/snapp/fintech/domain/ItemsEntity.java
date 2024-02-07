package com.snapp.fintech.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ITEMS")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createDate;

    private String name;

    private BigDecimal fee;

    private Integer amount;

    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private CategoryEntity category;
}
