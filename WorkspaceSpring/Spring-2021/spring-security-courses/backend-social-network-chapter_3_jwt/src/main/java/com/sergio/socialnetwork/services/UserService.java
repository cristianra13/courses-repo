package com.sergio.socialnetwork.services;

import java.util.Arrays;
import java.util.List;

import com.sergio.socialnetwork.dto.ImageDto;
import com.sergio.socialnetwork.dto.MessageDto;
import com.sergio.socialnetwork.dto.ProfileDto;
import com.sergio.socialnetwork.dto.SignUpDto;
import com.sergio.socialnetwork.dto.UserDto;
import com.sergio.socialnetwork.dto.UserSummaryDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public ProfileDto getProfile(Long userId) {
        return new ProfileDto(new UserSummaryDto(1L, "cristian", "Real"),
                Arrays.asList(new UserSummaryDto(2L, "John", "Doe")),
                Arrays.asList(new MessageDto(1L, "My message")),
                Arrays.asList(new ImageDto(1L, "Title", null)));
    }

    public void addFriend(Long friendId) {
        // nothing to do at the moment
    }

    public List<UserSummaryDto> searchUsers(String term) {
        return Arrays.asList(new UserSummaryDto(1L, "Cristian", "Real"),
                new UserSummaryDto(2L, "John", "Doe"));
    }

    public UserDto signUp(SignUpDto user) {
        return new UserDto(1L, "Cristian", "Real", "login", "token");
    }

    public void signOut(UserDto user) {
        // nothing to do at the moment
    }
}
