package com.lorre.userapplication.gateway.userregistrationtoken;

import com.lorre.userapplication.model.userregistrationtoken.UserRegistrationToken;

import java.util.List;
import java.util.UUID;

public interface UserRegistrationTokenGateway {

    UUID createUserRegistrationToken(UserRegistrationToken userRegistrationToken);
    UserRegistrationToken getUserRegistrationTokenById(UUID userRegistrationTokenId);
    void updateUserRegistrationToken(UserRegistrationToken userRegistrationToken);
    List<UserRegistrationToken> getUserRegistrationTokensByUserId(Long userId);
}
