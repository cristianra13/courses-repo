package com.app.social.network.services;

import com.app.social.network.dto.ImageDto;
import com.app.social.network.dto.MessageDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
public class CommunityService
{
    public List<MessageDto> getCommunityMessages(int page)
    {
        return Arrays.asList(new MessageDto(1L, "First Message"),
                new MessageDto(2L, "Second Message"));
    }

    public List<ImageDto> getCommunityImages(int page)
    {
        return Arrays.asList(new ImageDto(1L, "First Image", null),
                new ImageDto(2L, "Second Image", null));
    }

    public MessageDto postMessage(MessageDto mesaMessageDto)
    {
        return new MessageDto(3L, "New Message");
    }

    public ImageDto postImage(MultipartFile file, String title)
    {
        return new ImageDto(3L, "New Image", null);
    }
}
