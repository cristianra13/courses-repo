package com.app.social.network.dto;

import lombok.Data;

@Data
public class ImageDto
{
    private Long id;
    private String title;
    private byte[] content;

    public ImageDto(Long id, String title, byte[] content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public ImageDto() {
    }

}
