package com.app.social.network.dto;

import lombok.Data;

@Data
public class CredentialsDto
{
    private String login;
    private char[] password;

    public CredentialsDto() {
        super();
    }

    public CredentialsDto(String login, char[] password) {
        this.login = login;
        this.password = password;
    }
}
