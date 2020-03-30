package com.app.auth.domain.common;

import lombok.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceStatus {

    private String statusCode;
    private boolean success;
    private String errorMessage;
}
