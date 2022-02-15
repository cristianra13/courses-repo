package com.sergio.socialnetwork.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String token;

    public UserDto() {
        super();
    }

    public UserDto(Long id, String firstName, String lastName, String login, String token) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.token = token;
    }
}
