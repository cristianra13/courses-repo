package com.lambda.aws.dto;

import java.io.Serializable;

public class ResponseDto implements Serializable
{
    public static final long serialVersionUID = 1L;
    private String username;
    private String userStatus;
    private String enable;
    private ErrorDto error;
}
