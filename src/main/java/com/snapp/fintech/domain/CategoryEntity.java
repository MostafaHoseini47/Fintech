package com.snapp.fintech.domain;

import com.snapp.fintech.config.constant.AppConstants;
import com.snapp.fintech.domain.audit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = AppConstants.CATEGORY)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Boolean isDeleted = Boolean.FALSE;

}
