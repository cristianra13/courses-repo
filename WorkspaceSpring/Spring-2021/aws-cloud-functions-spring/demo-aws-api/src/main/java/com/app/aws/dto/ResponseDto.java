package com.app.aws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto
{
    private RequestDto request;
    private int status;
    private boolean error;
    private String message;
}
