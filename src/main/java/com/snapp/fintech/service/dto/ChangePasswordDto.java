package com.snapp.fintech.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {

    private String username;

    private String oldPassword;

    private String newPassword;

}
