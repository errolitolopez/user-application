package com.lorre.userapplication.usecase.userregistrationtoken;

import com.lorre.userapplication.entity.userregistrationtoken.UserRegistrationTokenEntity;

import java.util.UUID;

public interface CreateUserRegistrationToken {

    UUID create (UserRegistrationTokenEntity userRegistrationTokenEntity);
}
