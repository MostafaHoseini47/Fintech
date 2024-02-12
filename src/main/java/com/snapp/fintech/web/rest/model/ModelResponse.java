package com.snapp.fintech.web.rest.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelResponse {

    private String message;

    private Integer code;
}
