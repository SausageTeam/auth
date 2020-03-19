package com.app.auth.domain.token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token {

    private int validDuration;

    private String createdBy;

}
