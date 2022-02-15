package com.lambda.aws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto implements Serializable
{
    public static final long serialVersionUID = 1L;

    private int status;
    private boolean error;
    private String message;
    private String reason;
}
