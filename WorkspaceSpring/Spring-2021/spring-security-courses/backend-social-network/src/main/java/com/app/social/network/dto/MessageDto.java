package com.app.social.network.dto;

import lombok.Data;

@Data
public class MessageDto
{
    private Long id;
    private String content;

    public MessageDto(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public MessageDto() {
    }
}

