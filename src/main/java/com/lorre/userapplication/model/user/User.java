package com.lorre.userapplication.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String mobilePhone;
    private String externalId;
    private LocalDateTime creationDate;
    private LocalDateTime lastLogin;
    private boolean isActive;
}
