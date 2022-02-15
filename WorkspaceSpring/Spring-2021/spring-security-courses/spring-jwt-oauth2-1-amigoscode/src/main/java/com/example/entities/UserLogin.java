package com.example.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class UserLogin
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean isActive;
}
