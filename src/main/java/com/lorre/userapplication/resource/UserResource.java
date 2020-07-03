package com.lorre.userapplication.resource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class UserResource {
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
