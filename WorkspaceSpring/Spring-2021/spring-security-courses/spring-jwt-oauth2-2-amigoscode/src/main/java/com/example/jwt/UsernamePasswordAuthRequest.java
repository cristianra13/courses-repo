package com.example.jwt;

import lombok.Data;

@Data
public class UsernamePasswordAuthRequest
{
    private String username;

    private String password;
}
