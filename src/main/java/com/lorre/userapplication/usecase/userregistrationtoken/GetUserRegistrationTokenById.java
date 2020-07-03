package com.lorre.userapplication.usecase.userregistrationtoken;

import com.lorre.userapplication.entity.userregistrationtoken.UserRegistrationTokenEntity;

import java.util.UUID;

public interface GetUserRegistrationTokenById {

    UserRegistrationTokenEntity get(UUID userRegistrationTokenById);
}
