package com.app.test.security.dto;

import lombok.Data;

@Data
public class LoginResponseDto
{
    private String token;
    private boolean error;
    private String message;
    private String reason;
    private int status;
}
