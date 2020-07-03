package com.lorre.userapplication.usecase.userregistrationtoken;

import com.lorre.userapplication.entity.userregistrationtoken.UserRegistrationTokenEntity;

import java.util.List;

public interface GetUserRegistrationTokensByUserId {

    List<UserRegistrationTokenEntity> get(Long userId);
}
