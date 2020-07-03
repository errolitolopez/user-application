package com.lorre.userapplication.model.userregistrationtoken;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationToken {

    private UUID id;
    private LocalDateTime creationDate;
    private boolean isExpired;
    private Long userId;
}
