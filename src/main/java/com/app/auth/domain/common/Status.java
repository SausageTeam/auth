package com.app.auth.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Status {
    private boolean success;
    private String statusCode;
    private String errorMessage;
}
