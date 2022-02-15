package com.example.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class User implements Serializable
{
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String profileImageUrl;
    private LocalDate lastLoginDate;
    private LocalDate lastLoginDateDisplay;
    private LocalDate joinDate;
    private String[] roles; // ROLE_USER{ read, edit }, ROLE_ADMIN{ delete }, ...
    private String[] authorities;
    private boolean isActive;
    private boolean isNotLocked;

    public User() {}

    public User(Long id, String userId, String firstName, String lastName, String username, String password, String email, String profileImageUrl, LocalDate lastLoginDate, LocalDate lastLoginDateDisplay, LocalDate joinDate, String[] roles, String[] authorities, boolean isActive, boolean isNotLocked)
    {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginDateDisplay = lastLoginDateDisplay;
        this.joinDate = joinDate;
        this.roles = roles;
        this.authorities = authorities;
        this.isActive = isActive;
        this.isNotLocked = isNotLocked;
    }
}
