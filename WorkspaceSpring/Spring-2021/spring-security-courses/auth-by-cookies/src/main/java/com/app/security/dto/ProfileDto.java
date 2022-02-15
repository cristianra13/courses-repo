package com.app.security.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {

    private UserSummaryDto userDto;
    private List<UserSummaryDto> friends;
    private List<MessageDto> messages;
    private List<ImageDto> images;

    public ProfileDto() {
        super();
    }

    public ProfileDto(UserSummaryDto userDto, List<UserSummaryDto> friends, List<MessageDto> messages, List<ImageDto> images) {
        this.userDto = userDto;
        this.friends = friends;
        this.messages = messages;
        this.images = images;
    }

    public UserSummaryDto getUserDto() {
        return userDto;
    }
}
