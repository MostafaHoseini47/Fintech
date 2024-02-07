package com.snapp.fintech.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.snapp.fintech.domain.CategoryEntity;
import com.snapp.fintech.domain.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemsDto {

    private Long id;

    private String name;

    private BigDecimal fee;

    private Integer amount;

    private Long user;

    private Long category;
}
