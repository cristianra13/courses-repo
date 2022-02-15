package com.lambda.aws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto implements Serializable
{
    public static final long serialVersionUID = 1L;
    private String username;
    private String password;
}
