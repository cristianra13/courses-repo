package com.support.portal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    // @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String password;
    @Column(unique = true)
    private String email;
    private String profileImageUrl;
    private Date lastLoginDate;
    private Date lastLoginDateDisplay;
    private Date joinDate;
    private String role; // ROLE_USER{ read, edit }, ROLE_ADMIN{ delete }
    private String[] authorities;
    private boolean activeUser;
    private boolean notLockedUser;
}
