package com.app.social.network.dto;

import lombok.Data;

@Data
public class ErrorDto
{
    private String message;

    public ErrorDto(String message) {
        this.message = message;
    }

    public ErrorDto() {
        super();
    }

}
